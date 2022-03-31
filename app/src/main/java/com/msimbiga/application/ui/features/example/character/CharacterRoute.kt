package com.msimbiga.application.ui.features.example.character

import com.msimbiga.application.extensions.TAG
import com.msimbiga.application.navigation.NavigationDestination

object CharacterRoute : NavigationDestination {
    override val destination: String = "CHARACTER"

    val LIST_TAG = "${TAG}/LIST_TAG"
}
