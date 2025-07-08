package com.gibson.fobicx.navigation

sealed class Screen(val route: String ) {
    object Home : Screen("Home")
    object Materials : Screen("Materials")
    object Post : Screen("Post")
    object Stock : Screen("Stock")
    object Me : Screen("Me")
}
