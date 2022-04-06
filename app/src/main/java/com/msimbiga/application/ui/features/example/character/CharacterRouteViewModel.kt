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
    val characters: List<Character>

    object HasNoCharacters : HomeUiState {
        override val characters: List<Character> = emptyList()
    }

    data class HasCharacters(override val characters: List<Character>) : HomeUiState
}

data class HomeViewModelState(val characters: List<Character>) {
    fun toUiState(): HomeUiState =
        if (characters.isEmpty()) HomeUiState.HasNoCharacters
        else HomeUiState.HasCharacters(characters = characters)
}


@HiltViewModel
class CharacterRouteViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState(characters = listOf()))

    private val navigationEvents = MutableSharedFlow<CharacterDirections>()
    val navigationEventFlow = navigationEvents.asSharedFlow()

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

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun navigateToCharacterId(id: String) = safeLaunch {
        navigationEvents.emit(CharacterDirections.CharacterDetail(id))
    }

    fun handleEvent(event: CharacterRouteEvent) {
        Timber.d("Event is $event")
        when (event) {
            is CharacterRouteEvent.LoadData -> loadData()
            is CharacterRouteEvent.NavigateToId -> navigateToCharacterId(event.id)
        }
    }
}

sealed interface CharacterRouteEvent {
    data class NavigateToId(val id: String) : CharacterRouteEvent
    object LoadData : CharacterRouteEvent
}

sealed interface CharacterDirections {
    object Back : CharacterDirections
    data class CharacterDetail(val charId: String) : CharacterDirections
}
