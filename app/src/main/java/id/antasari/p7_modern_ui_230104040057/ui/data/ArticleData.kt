package id.antasari.p7_modern_ui_230104040057.data

import androidx.compose.ui.graphics.Color

data class Article(
    val id: Int,
    val title: String,
    val desc: String,
    val date: String,
    val color: Color
)

val articleList = listOf(
    Article(1, "Cara Menjaga Kesehatan Jantung", "Tips menjaga kesehatan jantung...", "16 Nov 2025", Color(0xFFADE8F4)),
    Article(2, "Bahaya Kurang Minum", "Dehidrasi adalah kondisi...", "15 Nov 2025", Color(0xFFCAF0F8)),
    Article(3, "Manfaat Jalan Pagi", "Aktivitas ringan ini berdampak besar...", "14 Nov 2025", Color(0xFF90E0EF)),
    Article(4, "Panduan Gizi Seimbang", "Makanan seimbang sangat penting...", "13 Nov 2025", Color(0xFF48CAE4))
)
