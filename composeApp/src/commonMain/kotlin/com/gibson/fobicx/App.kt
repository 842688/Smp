package com.gibson.fobicx

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun App() {
    var selectedTab by remember { mutableStateOf("Home") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selected = selectedTab,
                onSelect = { selectedTab = it }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when (selectedTab) {
                "Home" -> HomeScreen()
                "Materials" -> MaterialsScreen()
                "Post" -> PostScreen()
                "Stock" -> StockScreen()
                "Me" -> ProfileScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Text(
        text = "Home Screen",
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun MaterialsScreen() {
    Text(
        text = "Materials Screen",
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun PostScreen() {
    Text(
        text = "Post Screen",
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun StockScreen() {
    Text(
        text = "Stock Screen",
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun ProfileScreen() {
    Text(
        text = "Profile Screen",
        fontSize = 20.sp,
        style = MaterialTheme.typography.bodyLarge
    )
}
