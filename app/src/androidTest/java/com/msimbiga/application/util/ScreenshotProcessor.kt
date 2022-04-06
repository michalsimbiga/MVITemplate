package com.payeye.eyepos.util

import android.os.Environment.DIRECTORY_PICTURES
import android.os.Environment.getExternalStoragePublicDirectory
import androidx.test.runner.screenshot.BasicScreenCaptureProcessor
import com.payeye.eyepos.test.BuildConfig
import java.io.File

class MyScreenCaptureProcessor : BasicScreenCaptureProcessor() {

    init {
        this.mDefaultScreenshotPath = File(
            File(
                getExternalStoragePublicDirectory(DIRECTORY_PICTURES),
                "${BuildConfig.APPLICATION_ID}/${BuildConfig.BUILD_TYPE}"
            ).absolutePath,
            SCREENSHOTS_FOLDER_NAME
        )
    }

    override fun getFilename(prefix: String): String = prefix

    companion object {
        const val SCREENSHOTS_FOLDER_NAME = "screenshots"
    }
}