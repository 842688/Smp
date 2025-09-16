package com.gibson.fobicx.navigation

sealed class Screen(val route: String) {
    object Signup : Screen("signup")
    object Login : Screen("login")
    object AccountSetup : Screen("account_setup")
    object EmailVerify : Screen("email_verif")
    object PhoneVerify : Screen("phone_verif")
    object Home : Screen("home")
    object Marketplace : Screen("marketplace")
    object Portfolio : Screen("portfolio")
    object Me : Screen("me")

    companion object {
        fun fromRoute(route: String?): Screen? = when (route) {
            Signup.route -> Signup
            Login.route -> Login
            AccountSetup.route -> AccountSetup
            EmailVerify.route -> EmailVerify
            PhoneVerify.route -> PhoneVerify
            Home.route -> Home
            Marketplace.route -> Marketplace
            Portfolio.route -> Portfolio
            Me.route -> Me
            else -> null
        }
    }
}
