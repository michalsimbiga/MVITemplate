package com.msimbiga.data.utils

import com.msimbiga.domain.errors.ProjectError
import com.payeye.eyepos.network.utils.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

class NetworkHandler {
    @Suppress("ThrowsCount", "TooGenericExceptionCaught")
    suspend fun <T> safeNetworkCall(
        errorMapper: (Throwable) -> Throwable = { it },
        block: suspend CoroutineScope.() -> ApiResult<T>
    ): T =
        coroutineScope {
            try {
                when (val apiResult: ApiResult<T> = block()) {
                    is ApiResult.Success -> apiResult.data!!
                    is ApiResult.Failure -> {
                        if (apiResult.apiError != null) {
//                            throw apiResult.apiError.toPayEyeApiException()
                            throw ProjectError.Unknown
                        } else {
                            throw ProjectError.Unknown
                        }
                    }
                    else -> throw ProjectError.Unknown
                }
            } catch (throwable: Throwable) {
                throw errorMapper(throwable)
            }
        }

//    private fun ApiError.toPayEyeApiException() = PayEyeApiException(
//        status = status,
//        timestamp = timestamp,
//        debugMessage = debugMessage,
//        errorCode = errorCode,
//        message = message
//    )
}
