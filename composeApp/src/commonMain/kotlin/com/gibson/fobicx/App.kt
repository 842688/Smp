package com.gibson.fobicx

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.gibson.fobicx.navigation.Screen
import com.gibson.fobicx.screens.*
import com.gibson.fobicx.ui.components.BottomNavBar
import com.gibson.fobicx.ui.theme.FobicxTheme

@Composable
fun App() {
    FobicxTheme(useDarkTheme = true) {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Scaffold(
            bottomBar = {
                BottomNavBar(
                    currentRoute = currentRoute,
                    onItemClick = { route ->
                        if (route != currentRoute) {
                            navController.navigate(route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) { HomeScreen() }
                composable(Screen.Materials.route) { MarketScreen() }
                composable(Screen.Post.route) { PostScreen() }
                composable(Screen.Stock.route) { StockScreen() }
                composable(Screen.Me.route) {
                    ProfileScreen(
                        onAccountClick = {
                            navController.navigate("account_details")
                        },
                        navController = navController
                    )
                }
            }
        }
    }
}
