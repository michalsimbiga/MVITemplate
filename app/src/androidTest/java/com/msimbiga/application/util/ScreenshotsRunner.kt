package com.payeye.eyepos.util

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.payeye.eyepos.coreui.lottie.PayeyeLottieAnimator
import dagger.hilt.android.testing.HiltTestApplication

class ScreenshotsRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        PayeyeLottieAnimator.isDuringTests = true
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }

    override fun onCreate(arguments: Bundle) {
        super.onCreate(arguments)

        Robot.screenshotsEnabled = true
    }
}
