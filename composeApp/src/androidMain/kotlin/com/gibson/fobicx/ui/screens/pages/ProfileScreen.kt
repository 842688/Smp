package com.gibson.fobicx.ui.screens.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel()
) {
    var fullName by remember { mutableStateOf("Loading...") }
    var email by remember { mutableStateOf("Loading...") }
    var avatarUrl by remember { mutableStateOf("https://via.placeholder.com/64") }
    var role by remember { mutableStateOf("guest") }
    var industry by remember { mutableStateOf("Unknown") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            FirebaseFirestore.getInstance().collection("users").document(user.uid)
                .get()
                .addOnSuccessListener { doc ->
                    fullName = doc.getString("fullName") ?: "No Name"
                    email = doc.getString("email") ?: "No Email"
                    avatarUrl = doc.getString("avatarUrl") ?: avatarUrl
                    role = doc.getString("role") ?: "user"
                    industry = doc.getString("industry") ?: "Unknown"
                    isLoading = false
                }
                .addOnFailureListener {
                    fullName = "Error"
                    email = "Failed to load"
                    isLoading = false
                }
        } else {
            fullName = "Guest"
            email = "Not logged in"
            isLoading = false
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        color = Color.Black
    ) {
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            Column {
                // Profile Header
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = avatarUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                    )
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(text = fullName, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = email, color = Color.Gray, fontSize = 14.sp)
                        Text(text = "Role: ${role.replaceFirstChar { it.uppercase() }}", color = Color.LightGray, fontSize = 12.sp)
                        Text(text = "Industry: $industry", color = Color.LightGray, fontSize = 12.sp)
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Quick Access by Role
                Text("Quick Access", color = Color.Gray, fontSize = 12.sp)

                when (role.lowercase()) {
                    "fabricator" -> {
                        ProfileButton("My Business") { /* navController.navigate(...) */ }
                        ProfileButton("My Calculations") { /* navController.navigate(...) */ }
                        ProfileButton("My Posts") { /* navController.navigate(...) */ }
                    }
                    "seller" -> {
                        ProfileButton("My Store") { /* navController.navigate(...) */ }
                        ProfileButton("My Products") { /* navController.navigate(...) */ }
                        ProfileButton("My Orders") { /* navController.navigate(...) */ }
                    }
                    "customer" -> {
                        ProfileButton("My Orders") { /* navController.navigate(...) */ }
                        ProfileButton("Favorites") { /* navController.navigate(...) */ }
                        ProfileButton("Following") { /* navController.navigate(...) */ }
                    }
                    else -> {
                        ProfileButton("Explore") { /* navController.navigate(...) */ }
                    }
                }

                Spacer(Modifier.height(24.dp))

                // General Options
                Text("General", color = Color.Gray, fontSize = 12.sp)
                ProfileButton("Edit Profile") { /* navController.navigate("edit_profile") */ }
                ProfileButton("Settings") { /* navController.navigate("settings") */ }

                Spacer(Modifier.height(24.dp))

                // Logout
                Row(
                    modifier = Modifier
                        .clickable {
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate("login") {
                                popUpTo(0)
                            }
                        }
                        .padding(vertical = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout",
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout", color = Color.Red, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun ProfileButton(label: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(text = label, color = Color.White, fontSize = 16.sp)
        }
    }
}
