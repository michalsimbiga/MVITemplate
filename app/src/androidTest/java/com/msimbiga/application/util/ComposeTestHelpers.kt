package com.payeye.eyepos.util

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performGesture
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.swipeLeft as _swipeLeft

fun ComposeRobot.inputText(tag: String, text: String) {
    composeTestRule.onNodeWithTag(tag).performTextInput(text)
}

fun ComposeRobot.assertTextDisplayed(text: String) {
    composeTestRule.onNodeWithText(text).assertIsDisplayed()
}

fun ComposeRobot.swipeLeft() {
    composeTestRule.onRoot().performGesture { _swipeLeft() }
}
