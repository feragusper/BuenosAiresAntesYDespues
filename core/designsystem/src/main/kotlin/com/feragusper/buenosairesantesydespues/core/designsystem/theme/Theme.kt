package com.feragusper.buenosairesantesydespues.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Brand palette (teal "después" + sepia "antes").
private val Teal = Color(0xFF5E7379)
private val TealLight = Color(0xFF9EC0C7)
private val Sepia = Color(0xFFD7BE9C)

private val DarkColors = darkColorScheme(
    primary = TealLight,
    onPrimary = Color(0xFF10201F),
    secondary = Sepia,
    onSecondary = Color(0xFF241A0B),
    background = Color(0xFF0E1416),
    onBackground = Color(0xFFE6EBEC),
    surface = Color(0xFF161D1F),
    onSurface = Color(0xFFE6EBEC),
    surfaceVariant = Color(0xFF212B2D),
    onSurfaceVariant = Color(0xFFBDC8C9),
    outline = Color(0xFF5A6668),
)

private val LightColors = lightColorScheme(
    primary = Teal,
    onPrimary = Color.White,
    secondary = Color(0xFF8A6D3F),
    onSecondary = Color.White,
    background = Color(0xFFF7F9F9),
    onBackground = Color(0xFF171D1E),
    surface = Color.White,
    onSurface = Color(0xFF171D1E),
    surfaceVariant = Color(0xFFDBE4E5),
    onSurfaceVariant = Color(0xFF3F484A),
    outline = Color(0xFF6F797B),
)

/**
 * App theme with full light and dark support, following the system setting.
 */
@Composable
fun BaadTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = BaadTypography,
        content = content,
    )
}
