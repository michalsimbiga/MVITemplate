package com.msimbiga.application.ui.features.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.msimbiga.application.navigation.Navigator
import com.msimbiga.application.navigation.NavigatorEvent
import com.msimbiga.application.ui.features.example.NavGraphs
import com.msimbiga.application.utils.ComposeFlowCollector
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.rememberNavHostEngine
import timber.log.Timber

@Composable
fun MainScreen() {
    val engine = rememberNavHostEngine()

    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = engine,
    )
}
