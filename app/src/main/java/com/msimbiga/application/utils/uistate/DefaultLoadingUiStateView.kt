package com.msimbiga.application.utils.uistate

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable


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
