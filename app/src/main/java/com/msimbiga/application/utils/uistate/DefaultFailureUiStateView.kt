package com.msimbiga.application.utils.uistate

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.msimbiga.application.utils.BaseViewModel
import com.msimbiga.application.utils.UiStateData

@Composable
fun <SD : UiStateData> DefaultFailureUiStateView(
    throwable: Throwable,
    viewModel: BaseViewModel<SD>,
) {
    /**
     *
     * Handle generic errors here
     * For example
     *
     * if (throwable is SelfieCompressorException) {
     *      ErrorStatusView(
     *          title = stringResource(id = R.string.error),
     *          onButtonClick = {
     *              ...
     *          }
     *      )
     * } else {
     *      ErrorStatusView(
     *          onButtonClick = {
     *              viewModel.onRetry()
     *          }
     *      )
     * }
     *
     */

    Box() {
        Text("Failure View")
    }
}
