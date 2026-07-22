package com.feragusper.buenosairesantesydespues.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = Blue,
    background = Color.Black,
    onBackground = Color.White,
    surface = DarkGray,
    onSurface = Color.White,
    surfaceVariant = DarkGray,
    onSurfaceVariant = Color.White,
)

private val LightColors = lightColorScheme(
    primary = DarkGray,
    secondary = Blue,
)

/**
 * App theme. The original app was dark-only, so dark is the default; a light scheme is
 * provided for completeness and honored when the system requests it.
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
