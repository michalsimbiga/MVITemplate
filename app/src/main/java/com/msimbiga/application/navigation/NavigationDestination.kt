package com.msimbiga.application.navigation

interface NavigationDestination {
    val destination: String

    fun <T> parametrizedRoute(param: T): String = destination.plus("/{$param}")
    fun routeWithArg(arg: String): String = destination.plus("/$arg")
}
