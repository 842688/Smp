package com.gibson.fobicx

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gibson.fobicx.navigation.Screen
import com.gibson.fobicx.ui.BottomNavBar

@Composable
fun AppAndroid() {
    var currentScreen by remember { mutableStateOf(Screen.Home) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = currentScreen.route,
                onItemClick = { route ->
                    currentScreen = Screen.from(route)
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                is Screen.Home -> Text("🏠 Home Screen")
                is Screen.Materials -> Text("🧱 Materials Screen")
                is Screen.Post -> Text("➕ Post Screen")
                is Screen.Stock -> Text("📦 Stock Screen")
                is Screen.Me -> Text("👤 Profile Screen")
            }
        }
    }
}
