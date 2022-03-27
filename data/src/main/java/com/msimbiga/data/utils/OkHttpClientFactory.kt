package com.msimbiga.data.utils

import com.msimbiga.domain.AppConfiguration
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

interface OkHttpClientFactory {
    fun createOkHttpClient(
        appConfiguration: AppConfiguration,
        authenticator: Authenticator? = null,
        interceptors: List<Interceptor> = emptyList()
    ): OkHttpClient
}

class CustomOkHttpClientFactory : OkHttpClientFactory {
    override fun createOkHttpClient(
        appConfiguration: AppConfiguration,
        authenticator: Authenticator?,
        interceptors: List<Interceptor>
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
//            if (appConfiguration.sslCertificatePinEnabled) {
//                pinSSLCertificates(this, appConfiguration)
//            }

            connectTimeout(appConfiguration.httpConnectTimeout.toLong(), TimeUnit.SECONDS)
            writeTimeout(appConfiguration.httpWriteTimeout.toLong(), TimeUnit.SECONDS)
            readTimeout(appConfiguration.httpReadTimeout.toLong(), TimeUnit.SECONDS)

            interceptors.forEach {
                addInterceptor(it)
            }

            authenticator?.let {
                authenticator(it)
            }

            // This has to be last to log everything from previous interceptors
//            if (appConfiguration.debug) {
//                addLoggingInterceptor(this)

//            addLoggingInterceptor(
//
//            )
//
//            addInterceptor(
//                HttpLoggingInterceptor.Logger { message ->  Timber.d(message)}
//            )
        }.build()
    }

//    private fun pinSSLCertificates(
//        builder: OkHttpClient.Builder,
//        appConfiguration: AppConfiguration
//    ) {
//        val reversedPublicKeyHashes = appConfiguration.sslCertificatePinData
//
//        val certificatePinnerBuilder = CertificatePinner.Builder()
//
//        for (reversedPublicHash in reversedPublicKeyHashes) {
//            val publicKeyHash = buildShaHash(reversedPublicHash)
//            certificatePinnerBuilder.add(
//                appConfiguration.apiUrl,
//                publicKeyHash
//            )
//        }
//
//        builder.certificatePinner(certificatePinnerBuilder.build())
//    }

    private fun addLoggingInterceptor(builder: OkHttpClient.Builder) {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            if (!message.contains("�")) {
                Timber.d(message)
            } else {
                val shortMessage = message.take(if (message.length >= 500) 500 else message.length)
                Timber.d(shortMessage)
            }
        }.apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        builder.addInterceptor(loggingInterceptor)
    }

//    companion object {
//        @Suppress("MagicNumber")
//        fun buildShaHash(input: String): String {
//            return "s" + "h" + "a" + (200 + 50 + 6) + "/" + StringBuilder(input).reverse()
//                .toString()
//        }
//    }
}
