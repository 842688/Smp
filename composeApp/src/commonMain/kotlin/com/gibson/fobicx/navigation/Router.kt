package com.gibson.fobicx.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Small router to keep navigation logic in commonMain without adding a platform nav dependency.
 * Use Router.navigate(Screen.X.route) to change route.
 */
object Router {
    // initial route can be changed as needed (e.g., show login first)
    var currentRoute: String? by mutableStateOf(Screen.Home.route)
        private set

    fun navigate(route: String) {
        currentRoute = route
    }

    fun resetTo(route: String) {
        currentRoute = route
    }
}
