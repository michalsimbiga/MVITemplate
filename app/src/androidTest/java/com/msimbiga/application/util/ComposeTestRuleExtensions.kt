package com.payeye.eyepos.util

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onRoot
import androidx.test.runner.screenshot.Screenshot
import timber.log.Timber
import java.io.IOException

fun ComposeTestRule.saveScreenshot(filename: String) {
    val bitmap = onRoot().captureToImage().asAndroidBitmap()
    saveScreenshot(filename = filename, bitmap)
}


internal fun saveScreenshot(filename: String, bmp: Bitmap) {
    Timber.d("Taking screenshot of '$filename'")
    val screenCapture = Screenshot.capture()
    val processors = setOf(MyScreenCaptureProcessor())
    try {
        screenCapture.apply {
            name = filename
            process(processors)
        }
        Timber.d("Screenshot taken")
    } catch (ex: IOException) {
        Timber.e("Could not take a screenshot", ex)
    }
}

