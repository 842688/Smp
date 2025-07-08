package com.gibson.fobicx

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gibson.fobicx.navigation.Screen
import com.gibson.fobicx.screens.*
import com.gibson.fobicx.ui.components.BottomNavBar
import com.gibson.fobicx.ui.theme.FobicxTheme

@Composable
fun App() {
    FobicxTheme(useDarkTheme = true) {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

        Scaffold(
            bottomBar = {
                BottomNavBar(
                    currentRoute = currentScreen.route,
                    onItemClick = { selectedRoute ->
                        currentScreen = Screen.allScreens.find { it.route == selectedRoute } ?: Screen.Home
                    }
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                when (currentScreen) {
                    is Screen.Home -> HomeScreen()
                    is Screen.Materials -> MarketScreen()
                    is Screen.Post -> PostScreen()
                    is Screen.Stock -> StockScreen()
                    is Screen.Me -> ProfileScreen()
                }
            }
        }
    }
}
