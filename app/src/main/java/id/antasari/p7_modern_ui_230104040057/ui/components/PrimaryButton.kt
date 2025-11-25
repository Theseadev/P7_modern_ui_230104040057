package id.antasari.p7_modern_ui_230104040057.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text)
    }
}
