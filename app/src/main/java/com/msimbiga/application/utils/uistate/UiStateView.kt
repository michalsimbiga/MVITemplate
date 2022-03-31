package com.msimbiga.application.utils.uistate

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.msimbiga.application.extensions.alpha
import com.msimbiga.application.utils.*

@Composable
fun <D : UiStateData, VM : BaseViewModel<D>> UiStateView(
    viewModel: VM,
    renderOnLoading: @Composable ((D) -> Unit)? = null,
    renderOnFailure: @Composable ((error: Throwable) -> Unit)? = null,
    renderOnDefault: @Composable ((data: D) -> Unit)? = null,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.asLifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        initialState = viewModel.initialState
    )

    val uiStateData = uiState.getData()
    val loadingAlpha by animateFloatAsState(if (uiState is UiState.Loading) 1f else 0f)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (uiState) {
            is UiState.Default -> Default(renderOnDefault, uiStateData)
            is UiState.Failure -> Failure(renderOnFailure, uiState.asFailure.error, viewModel)
        }

        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha {
                    when (uiState) {
                        is UiState.Loading -> 1f
                        else -> 0f
                    }
                } .apply {
                    if (uiState is UiState.Loading) {
                        clickable {
                            // Enable clicks, so user can't click through this layer
                        }
                    }
                }

        ) {
            Loading(renderOnLoading, uiStateData)
        }
    }
}

@Composable
private fun <D : UiStateData> Loading(
    renderOnLoading: @Composable ((D) -> Unit)? = null,
    uiStateData: D,
) {
    if (renderOnLoading != null) {
        renderOnLoading.invoke(uiStateData)
    } else {
        DefaultLoadingUiStateView()
    }
}

@Composable
private fun <D : UiStateData> Default(
    renderOnDefault: @Composable ((D) -> Unit)? = null,
    uiStateData: D,
) {
    if (renderOnDefault != null) {
        renderOnDefault.invoke(uiStateData)
    } else {
        DefaultUiStateView()
    }
}

@Composable
private fun <D : UiStateData> Failure(
    renderOnFailure: @Composable ((error: Throwable) -> Unit)? = null,
    error: Throwable,
    viewModel: BaseViewModel<D>,
) {
    if (renderOnFailure != null) {
        renderOnFailure.invoke(error)
    } else {
        DefaultFailureUiStateView(error, viewModel)
    }
}
