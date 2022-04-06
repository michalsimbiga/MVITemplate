package com.msimbiga.application.di

import com.msimbiga.data.di.RepositoriesModule
import com.msimbiga.domain.models.Character
import com.msimbiga.domain.models.Mocks
import com.msimbiga.domain.repositories.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoriesModule::class]
)
class TestRepositoriesModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(): CharacterRepository =
        object : CharacterRepository {
            override suspend fun getCharacters(): List<Character> = listOf(Mocks.charMock)
            override suspend fun getCharacterById(id: String): Character = Mocks.charMock
        }
}