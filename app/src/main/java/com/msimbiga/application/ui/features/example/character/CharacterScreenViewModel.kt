package com.payeye.eyepos.ui.features.example.character

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import com.msimbiga.application.ui.features.example.character.CharacterNavigator
import com.msimbiga.application.utils.BaseViewModel
import com.msimbiga.application.utils.UiState
import com.msimbiga.application.utils.UiStateData
import com.msimbiga.domain.models.Character
import com.msimbiga.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterScreenViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val navigator: CharacterNavigator,
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel<CharacterScreenStateData>(
    initialState = UiState.Loading(CharacterScreenStateData())
) {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun loadData() = safeLaunch {
        val characters = getCharactersUseCase.invoke(Unit)
        updateUiStateToDefault { it.copy(characters = characters) }
    }

    fun navigateToCharacterId(id: String) = safeLaunch {
        navigator.navigateToCharacterId(id)
    }

    fun handleEvent(event: CharacterScreenEvent) {
        Timber.d("Event is $event")
        when (event) {
            is CharacterScreenEvent.LoadData -> loadData()
            is CharacterScreenEvent.NavigateToId -> navigateToCharacterId(event.id)
        }
    }
}

data class CharacterScreenStateData(
    val characters: List<Character> = emptyList()
) : UiStateData

sealed class CharacterScreenEvent {
    data class NavigateToId(val id: String) : CharacterScreenEvent()
    object LoadData : CharacterScreenEvent()
}
