package com.payeye.eyepos.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onRoot
import com.payeye.eyepos.coreui.theme.EyePosTheme
import com.payeye.eyepos.coreui.utils.UiState
import com.payeye.eyepos.coreui.utils.UiStateData
import com.payeye.eyepos.utils.base.BaseViewModel

open class ComposeRobot(
    val composeTestRule: ComposeContentTestRule
) : Robot() {

    fun setContent(isDarkTheme: Boolean = false, content: @Composable () -> Unit) {
        composeTestRule.setContent {
            EyePosTheme(darkTheme = isDarkTheme) { content() }
        }
    }

    fun capture(screenshotName: String) {
        if (screenshotsEnabled) {
            val bitmap = composeTestRule.onRoot().captureToImage().asAndroidBitmap()
            saveScreenshot(screenshotName, bitmap)
        }
    }

    fun advanceTimeBy(milliseconds: Long, ignoreFrameDuration: Boolean = false) =
        composeTestRule.mainClock.advanceTimeBy(
            milliseconds = milliseconds,
            ignoreFrameDuration = ignoreFrameDuration
        )

    fun withNoAutoAdvance(initialTimeAdvance: Long = 0, action: () -> Unit) =
        with(composeTestRule.mainClock) {
            advanceTimeBy(initialTimeAdvance)
            autoAdvance = false
            action()
            autoAdvance = true
        }
}

open class ComposeWithStateRobot<SD : UiStateData, VM : BaseViewModel<SD>>(
    composeTestRule: ComposeContentTestRule
) : ComposeRobot(composeTestRule) {

    protected lateinit var viewModel: VM

    fun setViewModel(viewModel: VM, initialUiState: UiState<SD>? = null) {
        this.viewModel = viewModel
        initialUiState?.let { setUiState(it) }
    }

    fun setSuccessState(data: SD) {
        setUiState(UiState.Default(data))
    }

    fun setFailureState(data: SD, error: Throwable) {
        setUiState(UiState.Failure(value = data, error = error))
    }

    open fun setUiState(newUiState: UiState<SD>) {
        onViewModel {
            updateUiState {
                newUiState
            }
        }
    }

    fun onViewModel(actions: VM.() -> Unit) {
        composeTestRule.runOnUiThread {
            if (::viewModel.isInitialized) {
                actions(viewModel)
            }
        }
    }

    fun setContentWithViewModel(
        isDarkTheme: Boolean = false,
        content: @Composable (viewModel: VM) -> Unit
    ) {
        composeTestRule.setContent {
            EyePosTheme(darkTheme = isDarkTheme) {
                content(viewModel)
            }
        }
    }
}
