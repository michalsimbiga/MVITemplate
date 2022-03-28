package com.msimbiga.application.ui.features.example.character

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msimbiga.application.navigation.NavigationDestination

object CharacterRoute : NavigationDestination {
    override val destination: String = "CHARACTER"

    val LIST_TAG = "${this::class.java.canonicalName}/LIST_TAG"

    fun NavGraphBuilder.includeCharacterRoute() {
        composable(route = destination) { CharacterScreen() }
    }
}
