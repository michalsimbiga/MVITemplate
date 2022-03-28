package com.msimbiga.application.ui.features.main

import com.msimbiga.application.navigation.Navigator
import com.msimbiga.application.navigation.NavigatorEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.updateAndGet
import javax.inject.Inject

class MainNavigator @Inject constructor(
) : Navigator {
    private val _flow = MutableStateFlow<NavigatorEvent?>(null)
    override val commandsFlow: StateFlow<NavigatorEvent?> = _flow

    override suspend fun navigate(command: NavigatorEvent) =
        _flow.updateAndGet { command } != null
}
