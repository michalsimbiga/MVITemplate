package com.msimbiga.data.utils

import com.msimbiga.data.models.ApiError
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.*
import timber.log.Timber
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.net.ssl.SSLException
import javax.net.ssl.SSLPeerUnverifiedException

sealed class ApiResult<out T> {
    data class Success<T>(val data: T? = null) : ApiResult<T>()
    data class Failure(val apiError: ApiError?) : ApiResult<Nothing>()
    object SSLPinningError : ApiResult<Nothing>()
    object SSLError : ApiResult<Nothing>()
    data class NetworkError(val error: Throwable) : ApiResult<Nothing>()
    data class OtherError(val error: Throwable) : ApiResult<Nothing>()
}

abstract class CallDelegate<TIn, TOut>(
    protected val proxy: Call<TIn>
) : Call<TOut> {
    override fun execute(): Response<TOut> = throw NotImplementedError()
    final override fun enqueue(callback: Callback<TOut>) = enqueueImpl(callback)
    final override fun clone(): Call<TOut> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted() = proxy.isExecuted
    override fun isCanceled() = proxy.isCanceled
    override fun timeout(): Timeout = proxy.timeout()

    abstract fun enqueueImpl(callback: Callback<TOut>)
    abstract fun cloneImpl(): Call<TOut>
}

class ResultCall<T>(
    proxy: Call<T>,
    private val apiErrorConverter: Converter<ResponseBody, ApiError>
) : CallDelegate<T, ApiResult<T>>(proxy) {

    @Suppress("MagicNumber", "TooGenericExceptionCaught")
    override fun enqueueImpl(callback: Callback<ApiResult<T>>) =
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val code = response.code()
                val result = if (code in 200 until 300) {
                    val body = response.body()
//                    Timber.d("response successful: ${body.toString()}")
                    ApiResult.Success(body)
                } else {
                    try {
                        val apiError = apiErrorConverter.convert(response.errorBody()!!)
                        ApiResult.Failure(apiError)
                    } catch (ex: Exception) {
                        Timber.e(ex, "unexpected error when parsing error message from server")
                        ApiResult.OtherError(ex)
                    }
                }
                callback.onResponse(this@ResultCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Timber.e(t, "failure on network call ")
                val result = when (t) {
                    is SSLPeerUnverifiedException -> ApiResult.SSLPinningError
                    is SSLException -> ApiResult.SSLError
//                    is NoInternetConnectionException -> ApiResult.NetworkError(t)
                    is IOException -> ApiResult.NetworkError(t)
                    else -> ApiResult.OtherError(t)
                }

                callback.onResponse(this@ResultCall, Response.success(result))
            }
        })

    override fun cloneImpl() = ResultCall(proxy.clone(), apiErrorConverter)
}

class ResultAdapter(
    private val type: Type,
    private val apiErrorConverter: Converter<ResponseBody, ApiError>
) : CallAdapter<Type, Call<ApiResult<Type>>> {
    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<ApiResult<Type>> =
        ResultCall(call, apiErrorConverter)
}

class ErrorHandlingCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ) = when (getRawType(returnType)) {
        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                ApiResult::class.java -> {
                    val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                    val apiErrorConverter: Converter<ResponseBody, ApiError> =
                        retrofit.responseBodyConverter(ApiError::class.java, emptyArray())
                    ResultAdapter(resultType, apiErrorConverter)
                }
                else -> null
            }
        }
        else -> null
    }
}
