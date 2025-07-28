package me.padamchopra.android.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode

private val DarkColorScheme = ColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF1C1C1C),
    onPrimaryContainer = Color.White,
    inversePrimary = Color.Black,

    secondary = Color.LightGray,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF2A2A2A),
    onSecondaryContainer = Color.White,

    tertiary = Color.Gray,
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF3A3A3A),
    onTertiaryContainer = Color.White,

    background = Color.Black,
    onBackground = Color.White,

    surface = Color(0xFF121212),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = Color.White,
    surfaceTint = Color.White,
    inverseSurface = Color.White,
    inverseOnSurface = Color.Black,

    error = Color.Red,
    onError = Color.Black,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFB4A9),

    outline = Color(0xFF8E8E8E),
    outlineVariant = Color(0xFF444444),
    scrim = Color.Black.copy(alpha = 0.7f),

    surfaceBright = Color(0xFF1E1E1E),
    surfaceContainer = Color(0xFF2C2C2C),
    surfaceContainerHigh = Color(0xFF383838),
    surfaceContainerHighest = Color(0xFF444444),
    surfaceContainerLow = Color(0xFF1A1A1A),
    surfaceContainerLowest = Color.Black,
    surfaceDim = Color(0xFF0F0F0F),
)

private val LightColorScheme = ColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE0E0E0),
    onPrimaryContainer = Color.Black,
    inversePrimary = Color.White,

    secondary = Color.DarkGray,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFF0F0F0),
    onSecondaryContainer = Color.Black,

    tertiary = Color.Gray,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFEDEDED),
    onTertiaryContainer = Color.Black,

    background = Color.White,
    onBackground = Color.Black,

    surface = Color.White,
    onSurface = Color.Black,
    surfaceVariant = Color(0xFFF5F5F5),
    onSurfaceVariant = Color.Black,
    surfaceTint = Color.Black,
    inverseSurface = Color(0xFF121212),
    inverseOnSurface = Color.White,

    error = Color.Red,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD4),
    onErrorContainer = Color(0xFF410000),

    outline = Color(0xFF737373),
    outlineVariant = Color(0xFFCAC4D0),
    scrim = Color.Black.copy(alpha = 0.5f),

    surfaceBright = Color.White,
    surfaceContainer = Color(0xFFF9F9F9),
    surfaceContainerHigh = Color(0xFFF4F4F4),
    surfaceContainerHighest = Color(0xFFEFEFEF),
    surfaceContainerLow = Color(0xFFFCFCFC),
    surfaceContainerLowest = Color.White,
    surfaceDim = Color(0xFFE0E0E0),
)

@Composable
fun AndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val inspectionMode = LocalInspectionMode.current

    if (inspectionMode) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography
        ) {
            Surface(color = MaterialTheme.colorScheme.background) {
                content()
            }
        }
    } else {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}