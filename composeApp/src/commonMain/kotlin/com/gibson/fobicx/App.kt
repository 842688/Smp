package com.gibson.fobicx

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.navigation.compose.NavHost
import org.jetbrains.compose.navigation.compose.composable
import org.jetbrains.compose.navigation.compose.rememberNavController
import com.gibson.fobicx.screens.*
import com.gibson.fobicx.ui.components.BottomNavBar

@Composable
fun App() {
    val navController = rememberNavController()
    val currentRoute by remember {
        derivedStateOf { navController.currentBackStackEntry?.destination?.route ?: "home" }
    }

    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    currentRoute = currentRoute,
                    onItemClick = { route ->
                        if (route != currentRoute) {
                            navController.navigate(route)
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") { HomeScreen() }
                composable("market") { MarketScreen() }
                composable("post") { PostScreen() }
                composable("stock") { StockScreen() }
                composable("me") { ProfileScreen() }
            }
        }
    }
}
