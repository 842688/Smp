package com.gibson.fobicx.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gibson.fobicx.navigation.Router
import com.gibson.fobicx.navigation.Screen

/**
 * Combined Signup + Email verification screen.
 *
 * Flow:
 *  - User enters email + password and taps "Send verification"
 *  - TODO: call Firebase Auth / backend to create a pending user or send verification code
 *  - UI switches to verification input (same file)
 *  - On successful verification (press Verify), navigate to AccountSetup
 *
 * This file intentionally does not simulate backend verification. Replace TODOs
 * with real Firebase Auth integration using your google-services.json and
 * PlatformServices / expect/actual adapters as needed.
 */
@Composable
fun SignupEmailScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var verificationCode by remember { mutableStateOf("") }
    var step by remember { mutableStateOf(SignupStep.Form) }
    var infoText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        when (step) {
            SignupStep.Form -> {
                Text("Create account", style = MaterialTheme.typography.headlineSmall)
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Button(onClick = {
                    // TODO: replace with Firebase/Auth call to create-pre-user and send verification code
                    // e.g., PlatformAuth.sendVerificationEmail(email, password)
                    // For now, switch UI to verification step; backend integration goes here.
                    infoText = "A verification code was sent to $email (integrate real send)."
                    step = SignupStep.Verify
                }) {
                    Text("Send verification")
                }

                Button(onClick = { Router.navigate(Screen.Login.route) }) {
                    Text("Already have an account? Login")
                }

                if (infoText.isNotBlank()) {
                    Text(infoText)
                }
            }

            SignupStep.Verify -> {
                Text("Email verification", style = MaterialTheme.typography.headlineSmall)
                Text("Enter the verification code sent to your email.")
                OutlinedTextField(
                    value = verificationCode,
                    onValueChange = { verificationCode = it },
                    label = { Text("Verification code") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Button(onClick = {
                    // TODO: verify code with Firebase / backend.
                    // On success proceed to account setup.
                    Router.navigate(Screen.AccountSetup.route)
                }) {
                    Text("Verify and continue")
                }

                Button(onClick = {
                    // Allow user to go back and edit details
                    step = SignupStep.Form
                }) {
                    Text("Back")
                }
            }
        }
    }
}

private enum class SignupStep { Form, Verify }
