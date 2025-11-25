package id.antasari.p7_modern_ui_230104040057

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.antasari.p7_modern_ui_230104040057.ui.navigation.BottomNavigationBar
import id.antasari.p7_modern_ui_230104040057.ui.screens.*

@Composable
fun AppNavigation(
    darkMode: Boolean,
    onThemeToggle: () -> Unit
) {

    val navController: NavHostController = rememberNavController()

    // cek halaman aktif
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    // sembunyikan bottom nav di halaman login
    val showBottomBar = currentRoute != "login"

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding: PaddingValues ->

        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("login") {
                LoginScreen(
                    onLogin = {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onRegister = {}
                )
            }

            composable("home") {
                HomeScreen(navController)
            }

            composable("profile") {
                ProfileScreen(userEmail = "fahrulbahri0520@gmail.com")
            }

            composable("settings") {
                SettingsScreen(
                    isDark = darkMode,
                    onThemeToggle = onThemeToggle
                )
            }


            composable("articles") {
                ArticleListScreen(navController)
            }

            composable("article/{id}") { backStack ->
                val id = backStack.arguments?.getString("id")?.toIntOrNull() ?: 0
                ArticleDetailScreen(navController, id)
            }
        }
    }
}
