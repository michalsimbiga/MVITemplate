package com.msimbiga.application.ui.features.example.character

import androidx.compose.animation.ExperimentalAnimationApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@ExperimentalAnimationApi
@Module
@InstallIn(ViewModelComponent::class)
abstract class CharacterModule {

    @Binds
    abstract fun bindsCharacterNavigator(navigator: CharacterNavigatorImpl): CharacterNavigator
}
