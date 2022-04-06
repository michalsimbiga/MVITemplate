package com.msimbiga.application.features.details

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.msimbiga.application.BaseJunit4Test
import com.msimbiga.application.ui.features.example.detail.DetailsRouteViewModel
import com.msimbiga.application.ui.features.example.detail.DetailsScreenDirections
import com.msimbiga.application.ui.features.example.detail.DetailsScreenEvent
import com.msimbiga.domain.models.Mocks
import com.msimbiga.domain.usecases.GetCharacterByIdUseCase
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
class DetailsRouteViewModelTest : BaseJunit4Test() {

    private val handle: SavedStateHandle = mock() {
        onBlocking { get<String?>(any()) } doReturn Mocks.charMock.id.toString()
    }
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase = mock {
        onBlocking { invoke(any()) } doReturn Mocks.charMock
    }

    private lateinit var viewModel: DetailsRouteViewModel

    @Before
    fun setup() {
        viewModel = DetailsRouteViewModel(
            handle = handle,
            getCharacterByIdUseCase = getCharacterByIdUseCase
        )
    }

    @Test
    fun `on navigate back invokes navigator method`() = runTest {
        viewModel.navigationEventFlow.test {
            viewModel.navigateBack()
            val emission = awaitItem()

            Truth.assertThat(emission).isEqualTo(DetailsScreenDirections.Back)
        }
    }

    @Test
    fun `on navigate back event updates navigation flow`() = runTest {
        val event = DetailsScreenEvent.NavigateUp
        viewModel.navigationEventFlow.test {
            viewModel.handleEvent(event)
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(DetailsScreenDirections.Back)
        }
    }

    @Test
    fun `handle event LoadData invokes load data method`() = runTest {
        val charId = "1"
        val event = DetailsScreenEvent.LoadData(id = charId)

        viewModel.handleEvent(event)

        verify(getCharacterByIdUseCase, times(2)).invoke(charId)
    }
}
