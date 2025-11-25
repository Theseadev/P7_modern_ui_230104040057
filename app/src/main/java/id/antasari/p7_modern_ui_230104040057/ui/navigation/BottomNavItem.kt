package id.antasari.p7_modern_ui_230104040057.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {

    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Article : BottomNavItem("articles", "Artikel", Icons.Default.List)
    object Profile : BottomNavItem("profile", "Profil", Icons.Default.Person)
    object Settings : BottomNavItem("settings", "Pengaturan", Icons.Default.Settings)
}
