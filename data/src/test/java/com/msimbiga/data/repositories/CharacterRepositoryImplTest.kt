package com.payeye.eyepos.network.repositories

import com.google.common.truth.Truth
import com.msimbiga.data.models.CharacterListResponse
import com.msimbiga.data.models.toResponse
import com.msimbiga.data.repositories.CharacterRepositoryImpl
import com.msimbiga.data.services.CharacterApiService
import com.msimbiga.data.utils.NetworkHandler
import com.msimbiga.domain.errors.ProjectError
import com.msimbiga.domain.models.Mocks
import com.msimbiga.domain.repositories.CharacterRepository
import com.nhaarman.mockitokotlin2.*
import com.payeye.eyepos.network.utils.ApiResult
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CharacterRepositoryImplTest {

    private val characterApiService: CharacterApiService = mock()
    private val networkHandler: NetworkHandler = NetworkHandler()

    lateinit var repository: CharacterRepository

    @Before
    fun setup() {
        repository = CharacterRepositoryImpl(
            characterApiService = characterApiService,
            networkHandler = networkHandler
        )
    }

    @Test
    fun `repository getCharacters method invokes api service method`() = runTest {
        whenever(characterApiService.getCharacters()).thenAnswer {
            ApiResult.Success(
                CharacterListResponse(
                    info = Mocks.pageMock.toResponse(),
                    results = listOf(Mocks.charMock.toResponse())
                )
            )
        }
        val response = repository.getCharacters()

        verify(characterApiService, times(1)).getCharacters()
        Truth.assertThat(response).isEqualTo(listOf(Mocks.charMock))
    }

    @Test
    fun `repository getCharacterById method invokes api service method`() = runTest {
        whenever(characterApiService.getCharacterById(any())).thenAnswer {
            ApiResult.Success(Mocks.charMock.toResponse())
        }
        val param = "charId"

        val response = repository.getCharacterById(param)

        verify(characterApiService, times(1)).getCharacterById(param)
        Truth.assertThat(response).isEqualTo(Mocks.charMock)
    }

    @Test
    fun `repository getCharacters method invokes service method that throws error`() = runTest {
        whenever(characterApiService.getCharacters()).thenAnswer { throw ProjectError.Unknown }

        var catchedError = false

        try {
            repository.getCharacters()
        } catch (exception: Throwable) {
            Truth.assertThat(exception).isInstanceOf(ProjectError.Unknown::class.java)
        }

        verify(characterApiService, times(1)).getCharacters()
    }

    @Test
    fun `repository getCharacterById method invokes service method that throws error`() = runTest {
        whenever(characterApiService.getCharacterById(any())).thenAnswer { throw ProjectError.Unknown }
        val param = "charId"

        try {
            repository.getCharacterById(param)
        } catch (exception: Throwable) {
            Truth.assertThat(exception).isInstanceOf(ProjectError.Unknown::class.java)
        }

        verify(characterApiService, times(1)).getCharacterById(param)
    }
}
