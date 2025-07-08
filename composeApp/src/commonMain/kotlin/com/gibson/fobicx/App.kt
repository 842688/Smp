package com.gibson.fobicx

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.gibson.fobicx.screens.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gibson.fobicx.navigation.Screen
import com.gibson.fobicx.ui.components.BottomNavBar

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = currentScreen.route,
                onItemClick = { selectedRoute ->
                    currentScreen = Screen.allScreens.first { it.route == selectedRoute }
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
                is Screen.Materials -> MaterialsScreen()
                is Screen.Post -> PostScreen()
                is Screen.Stock -> StockScreen()
                is Screen.Me -> ProfileScreen()
            }
        }
    }
}

