package com.msimbiga.application.ui.features.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.msimbiga.application.navigation.Navigator
import com.msimbiga.application.navigation.NavigatorEvent
import com.msimbiga.application.ui.features.example.character.CharacterRoute
import com.msimbiga.application.ui.features.example.character.CharacterRoute.includeCharacterRoute
import com.msimbiga.application.ui.features.example.detail.DetailRoute.includeDetailRoute
import com.msimbiga.application.utils.ComposeFlowCollector

@Composable
fun MainScreen(navigator: Navigator) {
    val navController = rememberNavController()

    ComposeFlowCollector(
        flow = navigator.commandsFlow,
        lifecycleOwner = LocalLifecycleOwner.current
    ) {
        println("Navigation event is $it")

        when (val event = it) {
            is NavigatorEvent.NavigateUp -> navController.navigateUp()
            is NavigatorEvent.Directions -> navController.navigate(event.destination, event.builder)
        }
    }

    NavHost(
        navController = navController,
        startDestination = CharacterRoute.destination,
    ) {
        // Examples
        includeCharacterRoute()
        includeDetailRoute()
    }
}
