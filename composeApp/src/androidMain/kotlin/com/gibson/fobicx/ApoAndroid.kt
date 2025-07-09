package com.gibson.fobicx

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.gibson.fobicx.ui.BottomNavBar
import com.gibson.fobicx.navigation.Screen
import com.gibson.fobicx.screens.*

@Composable
fun AppAndroid() {
    val appState = remember { AppState() }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = appState.currentScreen.route,
                onItemClick = { route -> appState.currentScreen = Screen.from(route) }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (appState.currentScreen) {
                is Screen.Home -> HomeScreen()
                is Screen.Market -> MarketScreen()
                is Screen.Post -> PostScreen()
                is Screen.Stock -> StockScreen()
                is Screen.Me -> ProfileScreen()
            }
        }
    }
}
