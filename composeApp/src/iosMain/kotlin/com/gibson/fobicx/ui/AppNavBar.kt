package com.gibson.fobicx.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
actual fun AppNavBar(currentRoute: String?, onItemClick: (route: String) -> Unit) {
    // iOS: bottom pill-style navigation (same visuals as Android)
    val bg = MaterialTheme.colorScheme.surfaceVariant
    val selectedBg = Color(0xFF2BE26A)
    val pillShape = RoundedCornerShape(24.dp)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        color = bg
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val icons = listOf(
                Icons.Default.Home,
                Icons.Default.Public,
                Icons.Default.List,
                Icons.Default.AccountCircle
            )

            val routes = listOf(
                MainNavItems.getOrNull(0)?.route ?: "home",
                MainNavItems.getOrNull(1)?.route ?: "marketplace",
                MainNavItems.getOrNull(2)?.route ?: "portfolio",
                MainNavItems.getOrNull(3)?.route ?: "me"
            )

            routes.forEachIndexed { index, route ->
                val selected = currentRoute == route
                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .clip(if (selected) pillShape else CircleShape)
                        .clickable { onItemClick(route) }
                        .padding(horizontal = if (selected) 12.dp else 0.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(if (selected) selectedBg else MaterialTheme.colorScheme.surface),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icons.getOrNull(index) ?: Icons.Default.Home,
                                contentDescription = MainNavItems.getOrNull(index)?.label ?: route,
                                tint = if (selected) Color.Black else MaterialTheme.colorScheme.onSurface
                            )
                        }

                        if (selected) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = MainNavItems.getOrNull(index)?.label ?: "",
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
