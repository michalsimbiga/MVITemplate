package com.msimbiga.application.ui.features.example.detail

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import com.msimbiga.application.ui.features.example.destinations.DetailScreenDestination
import com.msimbiga.application.utils.BaseViewModel
import com.msimbiga.application.utils.UiState
import com.msimbiga.application.utils.UiStateData
import com.msimbiga.domain.models.Character
import com.msimbiga.domain.usecases.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val handle: SavedStateHandle,
//    private val navArgs: DetailScreenNavArgs,
    private val navigator: DetailNavigator,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : BaseViewModel<DetailsScreenStateData>(
    initialState = UiState.Loading(DetailsScreenStateData())
) {

    init {
        DetailScreenDestination.argsFrom(handle).charId.let { charId ->
            Timber.d("VUKO Handle char is $charId")
            loadCharacter(charId)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun loadCharacter(charId: String) = safeLaunch {
        val char = getCharacterByIdUseCase.invoke(charId)
        Timber.d("VUKO Char is ${char.id}")
        updateUiStateToDefault { it.copy(chosenChar = char) }
    }

    fun navigateBack() {
        safeLaunch { navigator.navigateBack() }
    }

    fun handleEvent(event: DetailsScreenEvent) {
        Timber.d("Event is $event")
        when (event) {
            is DetailsScreenEvent.LoadData -> loadCharacter(event.id)
            is DetailsScreenEvent.NavigateUp -> navigateBack()
        }
    }
}

data class DetailsScreenStateData(
    val chosenChar: Character? = null,
) : UiStateData

sealed class DetailsScreenEvent {
    object NavigateUp : DetailsScreenEvent()
    data class LoadData(val id: String) : DetailsScreenEvent()
}
