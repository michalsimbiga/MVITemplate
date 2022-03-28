package com.msimbiga.application.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach


@Composable
fun <T> ComposeFlowCollector(
    flow: Flow<T>,
    lifecycleOwner: LifecycleOwner,
    onEffectCallback: (T) -> Unit,
) {
    DisposableEffect(Unit) {
        val observer = flow
            .onEach { onEffectCallback(it) }
            .observeInLifecycle(lifecycleOwner.lifecycle)
            .observer

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
