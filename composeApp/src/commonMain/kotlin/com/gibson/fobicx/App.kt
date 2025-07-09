package com.gibson.fobicx

import androidx.compose.runtime.*
import com.gibson.fobicx.navigation.Screen

class AppState {
    var currentScreen by mutableStateOf<Screen>(Screen.Home)
}
