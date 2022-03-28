package com.msimbiga.application.ui.features.example.detail

import com.msimbiga.application.navigation.Navigator
import com.msimbiga.application.navigation.NavigatorEvent
import javax.inject.Inject

interface DetailNavigator {
    suspend fun navigateBack(): Boolean
}

class DetailNavigatorImpl @Inject constructor(
    private val navigator: Navigator,
) : DetailNavigator {

    override suspend fun navigateBack(): Boolean =
        navigator.navigate(NavigatorEvent.NavigateUp)
}
