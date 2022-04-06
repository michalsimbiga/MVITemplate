package com.msimbiga.application.features.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.msimbiga.application.BaseJunit4Test
import com.msimbiga.application.ui.features.example.character.CharacterDirections
import com.msimbiga.application.ui.features.example.character.CharacterRouteViewModel
import com.msimbiga.application.ui.features.example.character.CharacterRouteEvent
import com.msimbiga.application.ui.features.example.character.HomeUiState
import com.msimbiga.domain.models.Mocks
import com.msimbiga.domain.usecases.GetCharactersUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterRouteViewModelTest : BaseJunit4Test() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val characterList = listOf(Mocks.charMock)

    private val handle: SavedStateHandle = mock() {
        onGeneric { get(any()) } doReturn ""
    }
    private val getCharactersUseCase: GetCharactersUseCase = mock() {
        onBlocking { invoke(any()) } doReturn characterList
    }

    lateinit var viewModel: CharacterRouteViewModel

    @Before
    fun setup() {
        viewModel = CharacterRouteViewModel(
            handle = handle,
            getCharactersUseCase = getCharactersUseCase
        )
    }

//    @Test
//    fun `initial state has no posts`() = runTest {
//        Truth.assertThat(viewModel.uiState.value)
//            .isInstanceOf(HomeUiState.HasNoCharacters::class.java)
//    }

    @Test
    fun `load data invokes usecase`() = runTest {
        viewModel.loadData().join()

        verify(getCharactersUseCase, times(1)).invoke(any())
    }

    @Test
    fun `load data result present updates state to Success`() = runTest {
        viewModel.loadData().join()

        Truth.assertThat(viewModel.uiState.value)
            .isInstanceOf(HomeUiState.HasCharacters::class.java)
        Truth.assertThat(viewModel.uiState.value.characters).isEqualTo(characterList)
    }

    @Test
    fun `handle event LoadData invokes load data method`() = runTest {
        val event = CharacterRouteEvent.LoadData

        viewModel.handleEvent(event)

        verify(getCharactersUseCase, times(1)).invoke(any())
    }

    @Test
    fun `handle event NavigateToId invokes navigation`() = runTest {
        val charId = "1"
        val event = CharacterRouteEvent.NavigateToId(id = charId)
        viewModel.navigationEventFlow.test {
            viewModel.handleEvent(event)
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(CharacterDirections.CharacterDetail(charId = charId))
        }
    }

    @Test
    fun `on navigate to charId invokes navigator method`() = runTest {
        val charId = "2"

        viewModel.navigationEventFlow.test {
            viewModel.navigateToCharacterId(id = charId).join()
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(CharacterDirections.CharacterDetail(charId = charId))
        }
    }
}
