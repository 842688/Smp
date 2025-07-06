package com.gibson.fobicx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BottomNavBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    onPostClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Materials", Icons.Default.Calculate),
        NavItem("Stock", Icons.Default.List),
        NavItem("Me", Icons.Default.Person)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.take(2).forEachIndexed { index, item ->
                NavItemView(
                    item = item,
                    selected = selectedItem == index,
                    onClick = { onItemSelected(index) }
                )
            }

            Spacer(modifier = Modifier.width(56.dp)) // Space for the FAB

            items.drop(2).forEachIndexed { index, item ->
                NavItemView(
                    item = item,
                    selected = selectedItem == index + 2,
                    onClick = { onItemSelected(index + 2) }
                )
            }
        }

        FloatingActionButton(
            onClick = onPostClick,
            containerColor = MaterialTheme.colorScheme.primary,
            shape = CircleShape,
            modifier = Modifier
                .size(72.dp)
                .align(Alignment.Center)
                .shadow(8.dp, CircleShape)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Post", tint = Color.White)
        }
    }
}

@Composable
fun NavItemView(item: NavItem, selected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.label,
            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = item.label,
            fontSize = 12.sp,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

data class NavItem(val label: String, val icon: ImageVector)
