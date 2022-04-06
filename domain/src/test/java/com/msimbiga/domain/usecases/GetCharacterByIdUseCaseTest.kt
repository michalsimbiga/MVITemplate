package com.msimbiga.domain.usecases

import com.google.common.truth.Truth
import com.msimbiga.domain.models.Mocks
import com.msimbiga.domain.repositories.CharacterRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharacterByIdUseCaseTest {

    private val repository: CharacterRepository = mock() {
        onBlocking { getCharacterById(any()) } doReturn Mocks.charMock
    }

    lateinit var usecase: GetCharacterByIdUseCase

    @Before
    fun setup() {
        usecase = GetCharacterByIdUseCase(repository)
    }

    @Test
    fun `invokes repository method on call`() = runTest {
        val params = "charId"

        val result = usecase.invoke(params)

        verify(repository, times(1)).getCharacterById(params)
        Truth.assertThat(result).isEqualTo(Mocks.charMock)
    }
}
