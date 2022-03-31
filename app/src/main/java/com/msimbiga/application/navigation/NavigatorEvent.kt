package com.msimbiga.application.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptionsBuilder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.Direction

sealed class NavigatorEvent {
    object NavigateUp : NavigatorEvent()
    data class Destin(val destination: Direction) : NavigatorEvent()
    data class Directions(
        val destination: String,
        val arguments: List<NamedNavArgument>? = null,
        val builder: NavOptionsBuilder.() -> Unit = {},
    ) : NavigatorEvent()
}
