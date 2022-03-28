package com.msimbiga.application.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

fun Modifier.alpha(alphaProvider: () -> Float) = alpha(alphaProvider())
