package com.msimbiga.domain.usecases

import com.google.common.truth.Truth
import com.msimbiga.domain.models.Mocks
import com.msimbiga.domain.repositories.CharacterRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharactersUseCaseTest {

    private val repository: CharacterRepository = mock() {
        onBlocking { getCharacters() } doReturn listOf(Mocks.charMock)
    }

    lateinit var usecase: GetCharactersUseCase

    @Before
    fun setup() {
        usecase = GetCharactersUseCase(repository)
    }

    @Test
    fun `invokes repository method on call`() = runTest {
        val result = usecase.invoke()

        verify(repository, times(1)).getCharacters()
        Truth.assertThat(result).isEqualTo(listOf(Mocks.charMock))
    }
}
