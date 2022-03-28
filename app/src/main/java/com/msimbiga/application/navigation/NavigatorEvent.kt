package com.msimbiga.application.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptionsBuilder

sealed class NavigatorEvent {
    object NavigateUp : NavigatorEvent()
    data class Directions(
        val destination: String,
        val arguments: List<NamedNavArgument>? = null,
        val builder: NavOptionsBuilder.() -> Unit = {},
    ) : NavigatorEvent()
}
