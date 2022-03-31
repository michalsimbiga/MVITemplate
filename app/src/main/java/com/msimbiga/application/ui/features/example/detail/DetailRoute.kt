package com.msimbiga.application.ui.features.example.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msimbiga.application.extensions.TAG
import com.msimbiga.application.navigation.NavigationDestination

object DetailRoute : NavigationDestination {
    const val ARG_KEY = "DETAIL_ARGS"
    override val destination = "DETAIL/{$ARG_KEY}"

    val LIST_TAG = "${TAG}/LIST_TAG"
}
