package com.gibson.fobicx.ui

import androidx.compose.runtime.Immutable
import com.gibson.fobicx.navigation.Screen

@Immutable
data class NavItem(val route: String, val label: String)

/**
 * Bottom navigation items (mobile: bottom pill bar).
 * Order: Home | Marketplace | Portfolio | Me
 */
val MainNavItems = listOf(
    NavItem(route = Screen.Home.route, label = "Home"),
    NavItem(route = Screen.Marketplace.route, label = "Marketplace"),
    NavItem(route = Screen.Portfolio.route, label = "Portfolio"),
    NavItem(route = Screen.Me.route, label = "Me")
)
