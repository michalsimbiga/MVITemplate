package com.msimbiga.application.di

import com.msimbiga.application.AppConfig
import com.msimbiga.domain.AppConfiguration
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ConfigurationModule {

    @Binds
    @Singleton
    abstract fun bindAppConfiguration(appConfiguration: AppConfig): AppConfiguration
}
