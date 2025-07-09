package com.gibson.fobicx.ui

import androidx.compose.runtime.Composable

@Composable
expect fun BottomNavBar(
    currentRoute: String?,
    onItemClick: (String) -> Unit
)
