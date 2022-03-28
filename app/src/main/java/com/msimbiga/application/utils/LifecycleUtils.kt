package com.msimbiga.application.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FlowObserver<T>(
    lifecycle: Lifecycle,
    private val flow: Flow<T>,
    private val collector: suspend (T) -> Unit,
) {

    private var job: Job? = null
    var observer: LifecycleEventObserver =
        LifecycleEventObserver { source: LifecycleOwner, event: Lifecycle.Event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    job = source.lifecycleScope.launch {
                        flow.collect { collector(it) }
                    }
                }
                Lifecycle.Event.ON_STOP -> {
                    job?.cancel()
                    job = null
                }
                else -> Unit
            }
        }

    init {
        lifecycle.addObserver(observer)
    }
}

inline fun <reified T> Flow<T>.observeOnLifecycle(
    lifecycle: Lifecycle,
    noinline collector: suspend (T) -> Unit,
) = FlowObserver(lifecycle, this, collector)

fun <T> Flow<T>.observeInLifecycle(
    lifecycle: Lifecycle,
) = FlowObserver(lifecycle, this, {})

@Composable
fun <T> Flow<T>.asLifecycleAwareState(lifecycleOwner: LifecycleOwner, initialState: T) =
    lifecycleAwareState(lifecycleOwner, this, initialState)

@Composable
fun <T> lifecycleAwareState(
    lifecycleOwner: LifecycleOwner,
    flow: Flow<T>,
    initialState: T,
): State<T> {
    val lifecycleAwareStateFlow = remember(flow, lifecycleOwner) {
        flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    return lifecycleAwareStateFlow.collectAsState(initialState)
}
