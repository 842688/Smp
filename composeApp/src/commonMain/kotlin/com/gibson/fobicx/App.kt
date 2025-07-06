package com.gibson.fobicx

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gibson.fobicx.ui.components.BottomNavBar
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun App() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedItem = selectedTab,
                onItemSelected = { selectedTab = it },
                onPostClick = { selectedTab = -1 } // Post screen trigger
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                when (selectedTab) {
                    0 -> HomeScreen()
                    1 -> MaterialsScreen()
                    2 -> StockScreen()
                    3 -> MeScreen()
                    -1 -> PostScreen()
                    else -> Text("Unknown tab")
                }
            }
        }
    )
}

// Placeholder Composables for screens (you can replace with actual content)

@Composable
fun HomeScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text("Home Screen", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun MaterialsScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(Icons.Default.Calculate, contentDescription = "Materials", modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text("Materials Screen", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun StockScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(Icons.Default.List, contentDescription = "Stock", modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text("Stock Screen", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun MeScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(Icons.Default.Person, contentDescription = "Me", modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text("Profile Screen", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun PostScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(Icons.Default.Add, contentDescription = "Post", modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text("Create a Post", style = MaterialTheme.typography.headlineSmall)
    }
}
