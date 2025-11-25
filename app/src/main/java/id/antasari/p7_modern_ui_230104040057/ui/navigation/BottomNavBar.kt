package id.antasari.p7_modern_ui_230104040057.ui.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

val SoftBluePrimary = Color(0xFF82B1FF)

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Article,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    Box(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(12.dp, RoundedCornerShape(30.dp), clip = false)
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(30.dp)
            )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            items.forEach { item ->

                val selected = destination.isRouteActive(item.route)

                // Smooth color transition
                val iconTint by animateColorAsState(
                    if (selected) SoftBluePrimary
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )

                // Smooth scale animation
                val scale by animateFloatAsState(if (selected) 1.2f else 1f)

                // Smooth vertical padding (text appear/disappear)
                val textPadding by animateDpAsState(if (selected) 4.dp else 0.dp)

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            if (!selected) navController.navigate(item.route)
                        }
                        .animateContentSize() // animasi height saat teks muncul/hilang
                        .padding(vertical = textPadding)
                        .scale(scale),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = iconTint,
                        modifier = Modifier.size(26.dp)
                    )

                    if (selected) {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall,
                            color = SoftBluePrimary
                        )
                    }
                }
            }
        }
    }
}

private fun NavDestination?.isRouteActive(route: String): Boolean {
    return this?.hierarchy?.any { it.route == route } == true
}
