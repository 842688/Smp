package com.gibson.fobicx.ui.screens.auth


import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.Timestamp

@Composable
fun AccountSetupScreen(
    navController: NavController,
    onSkip: () -> Unit,
    onSave: () -> Unit
) {
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val uid = currentUser?.uid ?: return

    var fullName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var accountType by remember { mutableStateOf("") }
    var customAccountType by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }

    val accountTypes = listOf("Personal", "Business", "Organization", "Other")

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Set Up Your Account", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
                .clickable { imagePickerLauncher.launch("image/*") }
        ) {
            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Selected Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text("Tap to select image", modifier = Modifier.align(Alignment.Center))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dob,
            onValueChange = { dob = it },
            label = { Text("Date of Birth (e.g., 01/01/2000)") },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenuBox(
            selectedOption = accountType,
            options = accountTypes,
            onOptionSelected = { accountType = it }
        )

        if (accountType == "Other") {
            OutlinedTextField(
                value = customAccountType,
                onValueChange = { customAccountType = it },
                label = { Text("Specify Account Type") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = onSkip) {
                Text("Skip")
            }

            Button(onClick = {
                if (imageUri != null) {
                    val ref = storage.reference.child("profile_pictures/$uid.jpg")
                    ref.putFile(imageUri!!)
                        .addOnSuccessListener {
                            ref.downloadUrl.addOnSuccessListener { uri ->
                                imageUrl = uri.toString()
                                saveUserData(
                                    uid = uid,
                                    fullName = fullName,
                                    userName = userName,
                                    dob = dob,
                                    email = currentUser.email ?: "",
                                    accountType = if (accountType == "Other") customAccountType else accountType,
                                    imageUrl = imageUrl,
                                    context = context,
                                    onSave = onSave
                                )
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Image upload failed", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(context, "Please select a profile image", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save")
            }
        }
    }
}

private fun saveUserData(
    uid: String,
    fullName: String,
    userName: String,
    dob: String,
    email: String,
    accountType: String,
    imageUrl: String,
    context: android.content.Context,
    onSave: () -> Unit
) {
    val userDoc = FirebaseFirestore.getInstance().collection("users").document(uid)

    val userData = hashMapOf(
        "uid" to uid,
        "fullName" to fullName,
        "userName" to userName,
        "dob" to dob,
        "email" to email,
        "accountType" to accountType,
        "industry" to "",
        "role" to "customer",
        "joinedAt" to Timestamp.now(),
        "profilePicture" to imageUrl
    )

    userDoc.set(userData).addOnSuccessListener {
        Toast.makeText(context, "Account setup complete", Toast.LENGTH_SHORT).show()
        onSave()
    }.addOnFailureListener {
        Toast.makeText(context, "Failed to save account", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun DropdownMenuBox(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text("Account Type") },
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
}
