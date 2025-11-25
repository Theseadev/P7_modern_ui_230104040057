package id.antasari.p7_modern_ui_230104040057

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import id.antasari.p7_modern_ui_230104040057.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // Global dark mode state (di-handle AppNavigation)
            var darkMode by remember { mutableStateOf(false) }

            AppTheme(darkTheme = darkMode) {
                AppNavigation(
                    darkMode = darkMode,
                    onThemeToggle = { darkMode = !darkMode }
                )
            }
        }
    }
}
