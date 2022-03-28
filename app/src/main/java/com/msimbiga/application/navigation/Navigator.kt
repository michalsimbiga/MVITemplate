package com.msimbiga.application.navigation

import kotlinx.coroutines.flow.StateFlow

interface Navigator {
    val commandsFlow: StateFlow<NavigatorEvent?>

    suspend fun navigate(command: NavigatorEvent): Boolean
}
