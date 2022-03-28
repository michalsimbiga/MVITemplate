package com.msimbiga.application.di

import com.msimbiga.application.navigation.Navigator
import com.msimbiga.application.ui.features.main.MainNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindNavigator(navigator: MainNavigator): Navigator

}
