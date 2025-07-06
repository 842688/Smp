package com.gibson.fobicx

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource


import fobicx.composeapp.generated.resources.Res
import fobicx.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it },
                onPostClick = { /* TODO: Handle Post */ }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Selected: ${when (selectedItem) {
                    0 -> "Home"
                    1 -> "Materials"
                    2 -> "Stock"
                    3 -> "Me"
                    else -> "Unknown"
                }}")
            }
        }
    )
            }
