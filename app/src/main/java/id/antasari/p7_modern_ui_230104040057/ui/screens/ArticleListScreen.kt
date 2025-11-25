package id.antasari.p7_modern_ui_230104040057.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.antasari.p7_modern_ui_230104040057.data.Article
import id.antasari.p7_modern_ui_230104040057.data.articleList

// ---------------------------
// WARNA SOFT BLUE & NETRAL
// ---------------------------
val SoftBluePrimary = Color(0xFF82B1FF)   // Aksen biru lembut
val CardCream = Color(0xFFF5F5F5)          // Card abu-abu sangat terang, netral
val BackgroundWhite = Color(0xFFFFFFFF)    // Background putih bersih

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListScreen(
    navController: NavController
) {
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Semua") }

    val categories = listOf(
        "Semua", "Gizi", "Kebersihan",
        "Anak", "Penyakit", "Vaksin", "Mental"
    )

    val filteredArticles = articleList.filter { art ->
        (selectedCategory == "Semua" || art.title.contains(selectedCategory, true)) &&
                art.title.contains(searchText, true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Artikel Kesehatan") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SoftBluePrimary,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = BackgroundWhite
    ) { padding ->

        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .background(BackgroundWhite)
        ) {

            // --------------------------
            // SEARCH BAR
            // --------------------------
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = SoftBluePrimary) },
                placeholder = { Text("Cari artikel...", color = SoftBluePrimary.copy(alpha = 0.6f)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // --------------------------
            // CATEGORY CHIPS
            // --------------------------
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(categories) { category ->
                    val isSelected = selectedCategory == category

                    AssistChip(
                        onClick = { selectedCategory = category },
                        label = { Text(category) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = if (isSelected) SoftBluePrimary else CardCream,
                            labelColor = if (isSelected) Color.White else Color.Black
                        )
                    )
                }
            }

            // --------------------------
            // LIST ARTIKEL
            // --------------------------
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                if (filteredArticles.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Artikel tidak ditemukan.", color = SoftBluePrimary)
                        }
                    }
                }

                items(filteredArticles) { article ->
                    ArticleListItem(article = article) {
                        navController.navigate("article/${article.id}")
                    }
                }
            }
        }
    }
}

// --------------------------
// ITEM LIST ARTIKEL
// --------------------------
@Composable
fun ArticleListItem(
    article: Article,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardCream)
    ) {
        Row(Modifier.padding(16.dp)) {

            // Thumbnail Box
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(article.color, RoundedCornerShape(12.dp))
            )

            Spacer(Modifier.width(14.dp))

            Column(Modifier.weight(1f)) {
                Text(
                    article.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = SoftBluePrimary
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    article.desc,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    color = Color.Black.copy(alpha = 0.7f)
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    article.date,
                    style = MaterialTheme.typography.labelMedium,
                    color = SoftBluePrimary
                )
            }
        }
    }
}
