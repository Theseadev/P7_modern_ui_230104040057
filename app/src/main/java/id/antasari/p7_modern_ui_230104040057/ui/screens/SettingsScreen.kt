package id.antasari.p7_modern_ui_230104040057.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDark: Boolean,
    onThemeToggle: () -> Unit
) {

    var notifEnabled by remember { mutableStateOf(true) }
    var autoUpdate by remember { mutableStateOf(false) }

    var expandAccount by remember { mutableStateOf(false) }
    var expandSecurity by remember { mutableStateOf(false) }
    var expandAppInfo by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pengaturan") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4A90E2), // Header biru soft
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->

        // Column scrollable
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {

            /* ---------------- HEADER BIRU SOFT ---------------- */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF4A90E2), // Soft Blue
                                Color(0xFF6FB1FC)  // Gradien lebih terang
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Icon(
                        Icons.Default.Settings,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(70.dp)
                    )

                    Spacer(Modifier.height(10.dp))

                    Text(
                        "Pengaturan Aplikasi",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            /* ---------------- TAMPILAN ---------------- */
            SettingsCard(
                title = "Tampilan",
                icon = Icons.Default.Settings
            ) {
                ToggleItem(
                    title = "Dark Mode",
                    description = "Aktifkan mode gelap untuk kenyamanan mata",
                    checked = isDark,
                    onChecked = onThemeToggle
                )
            }

            Spacer(Modifier.height(12.dp))

            /* ---------------- NOTIFIKASI ---------------- */
            SettingsCard(
                title = "Notifikasi",
                icon = Icons.Default.Notifications
            ) {
                ToggleItem(
                    title = "Notifikasi Warga",
                    description = "Terima update informasi kesehatan terbaru",
                    checked = notifEnabled,
                    onChecked = { notifEnabled = !notifEnabled }
                )

                ToggleItem(
                    title = "Update Otomatis",
                    description = "Pembaruan otomatis aplikasi",
                    checked = autoUpdate,
                    onChecked = { autoUpdate = !autoUpdate }
                )
            }

            Spacer(Modifier.height(12.dp))

            /* ---------------- PENGATURAN AKUN ---------------- */
            ExpandableSection(
                expanded = expandAccount,
                onExpand = { expandAccount = !expandAccount },
                title = "Pengaturan Akun",
                icon = Icons.Default.AccountCircle
            ) {
                SettingsTextItem("Ubah Nama")
                SettingsTextItem("Ubah Email")
                SettingsTextItem("Ubah Password")
            }

            Spacer(Modifier.height(12.dp))

            /* ---------------- KEAMANAN ---------------- */
            ExpandableSection(
                expanded = expandSecurity,
                onExpand = { expandSecurity = !expandSecurity },
                title = "Keamanan",
                icon = Icons.Default.Lock
            ) {
                SettingsTextItem("Verifikasi Dua Langkah")
                SettingsTextItem("Riwayat Login")
                SettingsTextItem("Perangkat Terhubung")
            }

            Spacer(Modifier.height(12.dp))

            /* ---------------- INFO APLIKASI ---------------- */
            ExpandableSection(
                expanded = expandAppInfo,
                onExpand = { expandAppInfo = !expandAppInfo },
                title = "Tentang Aplikasi",
                icon = Icons.Default.Info
            ) {
                Text("Aplikasi Informasi Kesehatan Warga", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(6.dp))
                Text("Versi: 1.0.0", style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(10.dp))
                Text(
                    "Aplikasi ini berisi artikel kesehatan, informasi warga, dan edukasi penting untuk masyarakat.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

/* ------------------------- SettingsCard ------------------------- */
@Composable
fun SettingsCard(
    title: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.width(10.dp))
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(14.dp))
            content()
        }
    }
}

/* ------------------------- ToggleItem ------------------------- */
@Composable
fun ToggleItem(
    title: String,
    description: String,
    checked: Boolean,
    onChecked: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Medium)
            Text(
                description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = { onChecked() }
        )
    }
}

/* ------------------------- ExpandableSection ------------------------- */
@Composable
fun ExpandableSection(
    expanded: Boolean,
    onExpand: () -> Unit,
    title: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(Modifier.padding(16.dp)) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { onExpand() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.width(10.dp))
                    Text(title, style = MaterialTheme.typography.titleMedium)
                }

                Icon(
                    if (expanded) Icons.Default.KeyboardArrowUp
                    else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(Modifier.padding(top = 14.dp)) {
                    content()
                }
            }
        }
    }
}

/* ------------------------- Text Item ------------------------- */
@Composable
fun SettingsTextItem(text: String) {
    Text(text, style = MaterialTheme.typography.bodyMedium)
    Spacer(Modifier.height(6.dp))
}
