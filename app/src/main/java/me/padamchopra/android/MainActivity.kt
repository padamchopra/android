package me.padamchopra.android

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import me.padamchopra.android.composables.Preview_DotPatternUI
import me.padamchopra.android.design.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme = isSystemInDarkTheme()

            SetSystemBarsColors(isDarkTheme)

            AppTheme(darkTheme = isDarkTheme) {
                Preview_DotPatternUI()
            }
        }
    }
}

@Composable
internal fun SetSystemBarsColors(darkTheme: Boolean) {
    val view = LocalView.current
    val window = (view.context as Activity).window

    SideEffect {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val insetsController = WindowCompat.getInsetsController(window, view)
        insetsController.isAppearanceLightStatusBars = !darkTheme
        insetsController.isAppearanceLightNavigationBars = !darkTheme
    }
}
