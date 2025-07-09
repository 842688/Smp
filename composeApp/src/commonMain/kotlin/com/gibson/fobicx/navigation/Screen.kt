package com.gibson.fobicx.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Market : Screen("market")
    object Post : Screen("post")
    object Stock : Screen("stock")
    object Me : Screen("me")

    companion object {
        val allScreens = listOf(Home, Market, Post, Stock, Me)

        fun from(route: String): Screen {
            return allScreens.find { it.route == route } ?: Home
        }
    }
}
