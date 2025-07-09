package com.gibson.fobicx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import com.gibson.fobicx.ui.theme.FobicxTheme
import androidx.compose.ui.tooling.preview.Preview
import com.gibson.fobicx.ui.BottomNavBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
           FobicxTheme{
           AppAndroid()
            }
        }
    }
}
