package com.gibson.fobicx.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimary,
    background = White,
    surface = White,
    surfaceVariant = Color(0xFFF2F2F2),
    onSurface = Color.Black
)

private val DarkColors = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimary,
    background = DarkBackground,
    surface = DarkBackground,
    surfaceVariant = DarkSurfaceVariant,
    onSurface = OnSurface
)

@Composable
fun FobicxTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (useDarkTheme) DarkColors else LightColors,
        typography = Typography(), // Default Material 3
        content = content
    )
}
