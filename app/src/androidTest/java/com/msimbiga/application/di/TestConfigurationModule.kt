package com.msimbiga.application.di

import com.msimbiga.domain.AppConfiguration
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ConfigurationModule::class]
)
class TestConfigurationModule {

    @Provides
    @Singleton
    fun provideTestConfig(): AppConfiguration = TestConfig
}
