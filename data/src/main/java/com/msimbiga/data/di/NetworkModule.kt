package com.msimbiga.data.di

import com.msimbiga.data.services.CharacterApiService
import com.msimbiga.domain.AppConfiguration
import com.msimbiga.data.utils.ErrorHandlingCallAdapterFactory
import com.msimbiga.data.utils.NetworkHandler
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

    @Provides
    @Singleton
    fun provideOkHttp(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        appConfiguration: AppConfiguration,
        okHttpClient: OkHttpClient,
        errorHandlingCallAdapterFactory: ErrorHandlingCallAdapterFactory,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(appConfiguration.apiUri)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(errorHandlingCallAdapterFactory)
            .build()

    @Provides
    @Singleton
    fun provideErrorHandlingCallAdapterFactory(): ErrorHandlingCallAdapterFactory =
        ErrorHandlingCallAdapterFactory()

    @Provides
    @Singleton
    fun provideNetworkHandler(): NetworkHandler = NetworkHandler()

    @Provides
    @Singleton
    fun provideCharacterApiService(retrofit: Retrofit): CharacterApiService =
        retrofit.create(CharacterApiService::class.java)
}
