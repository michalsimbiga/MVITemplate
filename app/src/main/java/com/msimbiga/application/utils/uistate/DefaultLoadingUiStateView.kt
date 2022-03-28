package com.payeye.eyepos.composables.uistate

import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun DefaultLoadingUiStateView() {

    CircularProgressIndicator()

//    CenteredColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White.copy(alpha = 0.95f))
//    ) {
//        val composition by rememberLottieComposition(
//            spec = LottieCompositionSpec.RawRes(R.raw.loader)
//        )
//        val progress by PayeyeLottieAnimator.wrapAnimateLottieCompositionAsState(
//            composition = composition,
//            iterations = LottieConstants.IterateForever
//        )
//
//        LottieAnimation(
//            modifier = Modifier
//                .size(80.dp)
//                .align(Alignment.CenterHorizontally),
//            progress = progress,
//            composition = composition
//        )
//    }
}
