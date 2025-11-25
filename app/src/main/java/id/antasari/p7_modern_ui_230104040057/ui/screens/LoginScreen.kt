package id.antasari.p7_modern_ui_230104040057.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {

    // Soft Blue Custom Theme
    val SoftBlue = Color(0xFF6FA8FF)
    val SoftBlueLight = Color(0xFFA8C9FF)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showError by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }

    fun validate() {
        when {
            email.isBlank() || !email.contains("@") -> {
                errorText = "Email tidak valid!"
                showError = true
            }
            password.length < 6 -> {
                errorText = "Password minimal 6 karakter!"
                showError = true
            }
            else -> {
                loading = true
                showError = false
                onLogin()
                loading = false
            }
        }
    }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            // 🔵 HEADER GRADIENT — SOFT BLUE
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                SoftBlue,
                                SoftBlueLight
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Box(
                        modifier = Modifier
                            .size(95.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(0.28f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = "App Icon",
                            tint = Color.White,
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    Text(
                        "Aplikasi Kesehatan Warga",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Masuk untuk melanjutkan",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // FORM
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                // EMAIL FIELD
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        showError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null, tint = SoftBlue)
                    },
                    singleLine = true
                )

                // PASSWORD FIELD
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        showError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = SoftBlue)
                    },
                    singleLine = true
                )

                // ERROR MESSAGE
                AnimatedVisibility(
                    visible = showError,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Text(
                        errorText,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(Modifier.height(8.dp))

                // 🔵 LOGIN BUTTON (Soft Blue)
                Button(
                    onClick = { validate() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .shadow(8.dp, RoundedCornerShape(14.dp)),
                    enabled = !loading,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SoftBlue
                    )
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text(
                            "Masuk",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }
                }

                Spacer(Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Belum punya akun? ")

                    Text(
                        "Daftar",
                        color = SoftBlue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onRegister()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            FooterLight(SoftBlue)
        }
    }
}

/* -------------------------------
   FOOTER
-------------------------------- */
@Composable
fun FooterLight(softBlue: Color) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Divider(
            Modifier.padding(horizontal = 16.dp),
            color = softBlue.copy(alpha = 0.4f)
        )

        Spacer(Modifier.height(14.dp))

        Text(
            "Aplikasi Informasi Kesehatan Warga",
            color = softBlue.copy(alpha = 0.8f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            "© 2025",
            color = softBlue.copy(alpha = 0.6f),
            style = MaterialTheme.typography.labelMedium
        )
    }
}
