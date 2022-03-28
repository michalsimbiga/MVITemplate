package com.payeye.eyepos.ui.features.example.detail

import com.msimbiga.application.ui.features.example.detail.DetailNavigator
import com.msimbiga.application.ui.features.example.detail.DetailNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DetailModule {

    @Binds
    abstract fun bindsDetailNavigator(navigator: DetailNavigatorImpl): DetailNavigator
}
