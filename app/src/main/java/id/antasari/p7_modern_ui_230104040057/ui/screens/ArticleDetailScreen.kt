package id.antasari.p7_modern_ui_230104040057.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.antasari.p7_modern_ui_230104040057.data.Article
import id.antasari.p7_modern_ui_230104040057.data.articleList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    navController: NavController,
    articleId: Int
) {

    val article: Article? = articleList.firstOrNull { it.id == articleId }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(article?.title ?: "Detail Artikel")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        if (article == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Artikel tidak ditemukan.")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            /* --------------------------------------------------
                HEADER GRADIENT
            -------------------------------------------------- */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                article.color,
                                MaterialTheme.colorScheme.surface
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    article.title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            /* --------------------------------------------------
                META INFO
            -------------------------------------------------- */
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {

                Column(Modifier.padding(16.dp)) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Admin Puskesmas Antasari",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            article.date,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            /* --------------------------------------------------
                CONTENT
            -------------------------------------------------- */
            Column(
                Modifier.padding(horizontal = 16.dp)
            ) {

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = article.desc,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                )

                Spacer(Modifier.height(18.dp))

                Text(
                    """
                    Menjaga kesehatan adalah hal yang sangat penting bagi setiap individu.
                    Artikel ini memberikan berbagai wawasan tentang pentingnya merawat tubuh 
                    dengan pola hidup sehat dan kebiasaan positif lainnya.

                    Selain itu, setiap warga disarankan rutin berkonsultasi dengan fasilitas 
                    kesehatan terdekat untuk mengecek kondisi tubuh secara berkala.

                    Dengan menerapkan langkah sederhana seperti olahraga ringan, konsumsi 
                    makanan sehat, serta tidur yang cukup, kualitas hidup akan meningkat 
                    secara signifikan.
                    
                    Tetap jaga kesehatan, karena kesehatan adalah investasi jangka panjang.
                    """.trimIndent(),
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                )

                Spacer(Modifier.height(30.dp))
            }
        }
    }
}
