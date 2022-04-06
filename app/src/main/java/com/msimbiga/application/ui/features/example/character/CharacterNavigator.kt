package com.msimbiga.application.ui.features.example.character

import com.msimbiga.application.navigation.Navigator
import com.msimbiga.application.navigation.NavigatorEvent
import com.msimbiga.application.ui.features.example.destinations.DetailScreenDestination
import javax.inject.Inject

interface CharacterNavigator {
    suspend fun navigateToCharacterId(charId: String): Boolean
}

class CharacterNavigatorImpl @Inject constructor(
    private val navigator: Navigator,
) : CharacterNavigator {

    override suspend fun navigateToCharacterId(charId: String) =
        navigator.navigate(NavigatorEvent.Destin(DetailScreenDestination(charId = charId)))
}
