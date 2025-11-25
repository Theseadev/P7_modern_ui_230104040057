package id.antasari.p7_modern_ui_230104040057.ui.theme

import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = md_primary,
    secondary = md_secondary,
    tertiary = md_tertiary,
    background = md_background,
    surface = md_surface,
)

private val DarkColors = darkColorScheme(
    primary = md_dark_primary,
    secondary = md_dark_secondary,
    tertiary = md_dark_tertiary,
    background = md_dark_background,
    surface = md_dark_surface,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val ctx = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
