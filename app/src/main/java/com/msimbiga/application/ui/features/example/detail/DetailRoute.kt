package com.msimbiga.application.ui.features.example.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msimbiga.application.navigation.NavigationDestination
import com.payeye.eyepos.ui.features.example.detail.DetailScreen

object DetailRoute : NavigationDestination {
    const val ARG_KEY = "DETAIL_ARGS"
    override val destination = "DETAIL/{$ARG_KEY}"

    val LIST_TAG = "${this::class.java.canonicalName}/LIST_TAG"

    val arguments = listOf(navArgument(ARG_KEY) { type = NavType.StringType })

    fun NavGraphBuilder.includeDetailRoute() {
        composable(
            route = parametrizedRoute(ARG_KEY),
            arguments = arguments,
        ) {
            DetailScreen()
        }
    }
}
