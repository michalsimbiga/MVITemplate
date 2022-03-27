package com.msimbiga.data.di

import com.msimbiga.data.repositories.CharacterRepositoryImpl
import com.msimbiga.domain.repositories.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindsCharacterRepository(repository: CharacterRepositoryImpl): CharacterRepository
}
