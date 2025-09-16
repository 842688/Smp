package com.gibson.fobicx.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gibson.fobicx.ui.AppNavBar
import com.gibson.fobicx.ui.MainNavItems

/**
 * Top-level navigation composable that renders the content for each route
 * and places the platform AppNavBar. Keep screen contents simple placeholders
 * — replace with real Composables as you implement screens.
 *
 * This composable is platform-agnostic and uses the AppNavBar expect/actual pair.
 */
@Composable
fun AppNavigation() {
    val current = Router.currentRoute

    // Simple layout: this file intentionally keeps content simple.
    // Place AppNavBar in platform actuals/layout or wrap this composable in your Scaffold.
    Box(modifier = Modifier.fillMaxSize()) {
        when (current) {
            Screen.Signup.route -> PlaceholderScreen("Signup")
            Screen.Login.route -> PlaceholderScreen("Login")
            Screen.AccountSetup.route -> PlaceholderScreen("Account Setup")
            Screen.EmailVerify.route -> PlaceholderScreen("Email Verification")
            Screen.PhoneVerify.route -> PlaceholderScreen("Phone Verification")
            Screen.Home.route -> PlaceholderScreen("Home")
            Screen.Marketplace.route -> PlaceholderScreen("Marketplace")
            Screen.Portfolio.route -> PlaceholderScreen("Portfolio")
            Screen.Me.route -> PlaceholderScreen("Me")
            else -> PlaceholderScreen("Unknown")
        }
        // The AppNavBar is expected to render itself (bottom or side) according to platform.
        AppNavBar(currentRoute = current, onItemClick = { route -> Router.navigate(route) })
    }
}

@Composable
private fun PlaceholderScreen(name: String) {
    // Minimal placeholder. Replace with your real screen composables.
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Screen: $name")
    }
}
