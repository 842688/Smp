package com.gibson.fobicx.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gibson.fobicx.navigation.Screen

@Composable
actual fun BottomNavBar(
    currentRoute: String?,
   onItemClick: (String) -> Unit
) {
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.ShoppingCart,
        Icons.Default.Add,
        Icons.Default.List,
        Icons.Default.Person
    )

    val screens = Screen.allScreens

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.widthIn(max = 500.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                screens.forEachIndexed { index, screen ->
                    if (screen == Screen.Post) {
                        Spacer(modifier = Modifier.width(48.dp))
                    } else {
                        val isSelected = currentRoute == screen.route

                        Column(
                            modifier = Modifier
                                .clickable { onItemClick(screen.route) }
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = screen.route,
                                tint = if (isSelected)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = screen.route,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isSelected)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }

            FloatingActionButton(
                onClick = { onItemClick(Screen.Post.route) },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Post",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
