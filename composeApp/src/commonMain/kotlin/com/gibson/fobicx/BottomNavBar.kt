package com.gibson.fobicx

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Calculator
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigationBar(
    selected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Materials", Icons.Default.Calculator),
        NavItem("Post", Icons.Default.Add),
        NavItem("Stock", Icons.Default.Inventory),
        NavItem("Me", Icons.Default.Person)
    )

    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp,
        shape = RoundedCornerShape(50),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(modifier = Modifier.padding(vertical = 8.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEachIndexed { index, item ->
                    if (index == 2) {
                        // Center FAB
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            FloatingActionButton(
                                onClick = { onSelect(item.title) },
                                shape = CircleShape,
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .size(56.dp)
                                    .offset(y = (-20).dp)
                            ) {
                                Icon(item.icon, contentDescription = item.title)
                            }
                        }
                    } else {
                        Box(modifier = Modifier.weight(1f)) {
                            NavItemView(
                                item = item,
                                isSelected = selected == item.title,
                                onClick = { onSelect(item.title) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavItemView(item: NavItem, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = item.title,
            fontSize = 12.sp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

data class NavItem(
    val title: String,
    val icon: ImageVector
)
