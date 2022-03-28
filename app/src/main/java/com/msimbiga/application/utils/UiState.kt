package com.msimbiga.application.utils

interface UiStateData

sealed class UiState<out T : UiStateData>(private val value: T) {
    open fun getData(): T = value

    data class Loading<out T : UiStateData>(val value: T) : UiState<T>(value)
    data class Default<T : UiStateData>(val value: T) : UiState<T>(value)
    data class Failure<T : UiStateData>(val value: T, val error: Throwable) : UiState<T>(value)
}

val <T : UiStateData> UiState<T>.asLoading: UiState.Loading<T>
    get() = this as UiState.Loading<T>

val <T : UiStateData> UiState<T>.asDefault: UiState.Default<T>
    get() = this as UiState.Default<T>

val <T : UiStateData> UiState<T>.asFailure: UiState.Failure<T>
    get() = this as UiState.Failure<T>

