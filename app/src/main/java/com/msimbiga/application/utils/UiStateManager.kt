package com.msimbiga.application.utils

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface UiStateManager<SD : UiStateData> {
    val uiState: StateFlow<UiState<SD>>
    val currentStateData: SD

    fun updateUiState(updateFunc: (UiState<SD>) -> UiState<SD>)
    fun updateStateData(updateFunc: (SD) -> SD)
    fun updateUiStateToLoading()
    fun updateUiStateToDefault(updateFunc: ((SD) -> SD)? = null)
    fun updateUiStateToFailure(
        error: Throwable,
        updateFunc: ((SD) -> SD)? = null,
    )
}

class UiStateManagerDelegate<SD : UiStateData>(
    private val initialState: UiState<SD>,
) : UiStateManager<SD> {

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    val state: MutableStateFlow<UiState<SD>> by lazy { MutableStateFlow(initialState) }

    override val uiState: StateFlow<UiState<SD>> = state.asStateFlow()
    override val currentStateData
        get() = uiState.value.getData()

    override fun updateUiState(updateFunc: (UiState<SD>) -> UiState<SD>) {
        state.update(updateFunc)
    }

    override fun updateStateData(updateFunc: (SD) -> SD) {
        when (val currentState = uiState.value) {
            is UiState.Loading<*> -> updateUiState {
                UiState.Loading(updateFunc(currentStateData))
            }
            is UiState.Default<*> -> updateUiState {
                UiState.Default(updateFunc(currentStateData))
            }
            is UiState.Failure<*> -> updateUiState {
                UiState.Failure(updateFunc(currentStateData), currentState.error)
            }
        }
    }

    override fun updateUiStateToLoading() {
        updateUiState { UiState.Loading(uiState.value.getData()) }
    }

    override fun updateUiStateToDefault(updateFunc: ((SD) -> SD)?) {
        updateUiState {
            if (updateFunc != null) {
                UiState.Default(updateFunc.invoke(it.getData()))
            } else {
                UiState.Default(it.getData())
            }
        }
    }

    override fun updateUiStateToFailure(
        error: Throwable,
        updateFunc: ((SD) -> SD)?,
    ) {
        val newStateData = updateFunc?.invoke(uiState.value.getData()) ?: uiState.value.getData()
        updateUiState { UiState.Failure(newStateData, error) }
    }
}
