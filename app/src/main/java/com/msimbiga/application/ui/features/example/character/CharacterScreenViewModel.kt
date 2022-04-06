package com.msimbiga.application.ui.features.example.character

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.msimbiga.application.utils.BaseViewModel
import com.msimbiga.domain.models.Character
import com.msimbiga.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

sealed interface HomeUiState {

    object HasNoCharacters : HomeUiState

    data class HasCharacters(val characters: List<Character>) : HomeUiState
}

private data class HomeViewModelState(val characters: List<Character>) {
    fun toUiState(): HomeUiState =
        if (characters.isEmpty()) HomeUiState.HasNoCharacters
        else HomeUiState.HasCharacters(characters = characters)
}


@HiltViewModel
class CharacterScreenViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val navigator: CharacterNavigator,
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState(characters = listOf()))

    val uiState = viewModelState
        .map { it.toUiState() }
        .catch { Timber.d("VUKO Cancelable cached") }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun loadData() = safeLaunch {
        val characters = getCharactersUseCase.invoke(Unit)

        viewModelState.update { it.copy(characters = characters) }
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

sealed class CharacterScreenEvent {
    data class NavigateToId(val id: String) : CharacterScreenEvent()
    object LoadData : CharacterScreenEvent()
}
