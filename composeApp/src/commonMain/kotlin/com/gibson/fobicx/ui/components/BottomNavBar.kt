package com.gibson.fobicx.ui.components

import androidx.compose.runtime.Composable

expect fun BottomNavBar(
    currentRoute: String?,
    onItemClick: (String) -> Unit
)
