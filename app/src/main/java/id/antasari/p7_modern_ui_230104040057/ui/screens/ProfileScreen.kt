package id.antasari.p7_modern_ui_230104040057.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ============================
// SOFT BLUE PALETTE (VERSI ALT1)
// ============================
val SoftBlueAlt1 = Color(0xFF60A5FA)      // menggantikan SoftBluePrimary
val SoftBlueLight = Color(0xFFDCEEFB)
val SoftBlueVeryLight = Color(0xFFEFF6FF)
val SoftBlueDark = Color(0xFF1E40AF)
val NeutralBackground = Color(0xFFF5F7FA)

// ============================
// Name Parser
// ============================
fun getNameFromEmail(email: String): String {
    val username = email.substringBefore("@")
        .replace(Regex("\\d"), "")
        .lowercase()

    val parts = username.split(".", "_").filter { it.isNotBlank() }
    return if (parts.size >= 2) {
        parts.joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
    } else {
        username.replaceFirstChar { it.uppercase() }
    }
}

// ============================
// PROFILE SCREEN
// ============================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(userEmail: String = "fahrulbahri0520@gmail.com") {
    val displayName = getNameFromEmail(userEmail)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profil Warga") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SoftBlueAlt1,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = NeutralBackground
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            // HEADER — Soft Blue Gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                SoftBlueAlt1.copy(alpha = 0.7f),
                                SoftBlueLight.copy(alpha = 0.9f),
                                SoftBlueVeryLight
                            )
                        )
                    )
                    .padding(vertical = 26.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    // Avatar
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        SoftBlueAlt1.copy(alpha = 0.8f),
                                        SoftBlueLight.copy(alpha = 0.7f)
                                    )
                                )
                            )
                            .border(3.dp, SoftBlueVeryLight, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(65.dp)
                        )
                    }

                    Spacer(Modifier.height(14.dp))

                    Text(
                        displayName,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = SoftBlueDark
                    )

                    Text(
                        "Warga Kabupaten Antasari",
                        style = MaterialTheme.typography.bodyMedium,
                        color = SoftBlueDark.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // PERSONAL INFO CARD
            InfoCard(title = "Informasi Pribadi", icon = Icons.Default.Info) {
                ProfileItem(Icons.Default.Email, "Email", userEmail)
                Spacer(Modifier.height(10.dp))
                ProfileItem(Icons.Default.LocationOn, "Alamat", "Jl. Pemajatan Komp. Permata Hijau II")
                Spacer(Modifier.height(10.dp))
                ProfileItem(Icons.Default.Favorite, "Golongan Darah", "A")
            }

            Spacer(Modifier.height(16.dp))

            // HEALTH HISTORY CARD
            InfoCard(title = "Riwayat Kesehatan", icon = Icons.Default.Favorite) {
                HealthHistoryItem("Cek kesehatan terakhir", "12 Mei 2025")
                HealthHistoryItem("Tekanan darah", "Normal")
                HealthHistoryItem("Alergi", "Tidak ada")
            }

            Spacer(Modifier.height(30.dp))
        }
    }
}

// ============================
// COMPONENTS
// ============================
@Composable
fun InfoCard(
    title: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = SoftBlueDark)
                Spacer(Modifier.width(10.dp))
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                    fontWeight = FontWeight.Bold,
                    color = SoftBlueDark
                )
            }
            Spacer(Modifier.height(14.dp))
            content()
        }
    }
}

@Composable
fun ProfileItem(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = SoftBlueDark)
        Spacer(Modifier.width(10.dp))
        Column {
            Text(label, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text(value, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun HealthHistoryItem(title: String, value: String) {
    Column {
        Text(title, fontWeight = FontWeight.Medium, color = SoftBlueDark)
        Text(value, color = SoftBlueAlt1, fontSize = 15.sp)
        Spacer(Modifier.height(10.dp))
        Divider()
        Spacer(Modifier.height(10.dp))
    }
}
