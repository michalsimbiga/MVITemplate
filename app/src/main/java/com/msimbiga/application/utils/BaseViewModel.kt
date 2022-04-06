package com.msimbiga.application.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel() : ViewModel() {
//    UiStateManager<S> by UiStateManagerDelegate(initialState)

    protected fun safeLaunch(
        onFailure: ((Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit,
    ): Job = viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable, COROUTINE_EXCEPTION_HANDLER_MESSAGE)
        }) {
        runCatching { block() }
            .onFailure { throwable -> onFailure?.invoke(throwable) }
    }

//    // Override carefully, so you don't turn off error handling in ViewModel :)
//    fun updateErrorEffect(payEyeError: PayEyeError, onRetry: () -> Unit): ErrorEffect? {
//        return ErrorEffect(payEyeError, onRetry)
//    }

    companion object {
        private const val COROUTINE_EXCEPTION_HANDLER_MESSAGE = "COROUTINE_EXCEPTION_HANDLER"
    }
}
