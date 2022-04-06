package com.msimbiga.application.ui.features.example.detail

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.msimbiga.application.ui.features.example.destinations.DetailsRouteDestination
import com.msimbiga.application.utils.BaseViewModel
import com.msimbiga.domain.models.Character
import com.msimbiga.domain.usecases.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

data class DetailViewModelState(val character: Character? = null)

@HiltViewModel
class DetailsRouteViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : BaseViewModel() {

    private val viewModelState = MutableStateFlow(DetailViewModelState())

    private val navigationEvents = MutableSharedFlow<DetailsScreenDirections>()
    val navigationEventFlow = navigationEvents.asSharedFlow()

    val uiState = viewModelState
        .catch { Timber.d("VUKO Cancelable cached") }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value
        )

    init {
        DetailsRouteDestination.argsFrom(handle).charId.let { charId ->
            Timber.d("VUKO Handle char is $charId")
            loadCharacter(charId)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun loadCharacter(charId: String) = safeLaunch {
        val char = getCharacterByIdUseCase.invoke(charId)
        Timber.d("VUKO Char is ${char.id}")
        viewModelState.update { it.copy(character = char) }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun navigateBack() = safeLaunch { navigationEvents.emit(DetailsScreenDirections.Back) }

    fun handleEvent(event: DetailsScreenEvent) {
        Timber.d("Event is $event")
        when (event) {
            is DetailsScreenEvent.LoadData -> loadCharacter(event.id)
            is DetailsScreenEvent.NavigateUp -> navigateBack()
        }
    }
}

sealed interface DetailsScreenEvent {
    object NavigateUp : DetailsScreenEvent
    data class LoadData(val id: String) : DetailsScreenEvent
}

sealed interface DetailsScreenDirections {
    object Back : DetailsScreenDirections
}
