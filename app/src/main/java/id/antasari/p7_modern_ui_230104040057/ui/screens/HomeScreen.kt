package id.antasari.p7_modern_ui_230104040057.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import id.antasari.p7_modern_ui_230104040057.data.Article
import id.antasari.p7_modern_ui_230104040057.data.articleList
import id.antasari.p7_modern_ui_230104040057.R
import kotlinx.coroutines.delay
import androidx.compose.foundation.isSystemInDarkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val listState = rememberLazyListState()
    val colorScheme = MaterialTheme.colorScheme
    val isDark = isSystemInDarkTheme() // cek mode gelap

    Scaffold(
        containerColor = colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Halo Rul 👋", color = colorScheme.onBackground) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorScheme.surface
                )
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(colorScheme.background),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {

            item { BannerSlider() }
            item { SearchBarHome() }
            item { CategorySection() }

            item { SectionTitle("Artikel Terbaru") }

            items(articleList) { article ->
                ArticleCard(article, isDark) {}
            }

            item { Spacer(Modifier.height(80.dp)) }
        }
    }
}

/* --------------------------------------------------
   BANNER SLIDER
-------------------------------------------------- */
@Composable
fun BannerSlider() {

    val banners = listOf(
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3
    )

    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            currentIndex = (currentIndex + 1) % banners.size
        }
    }

    val colorScheme = MaterialTheme.colorScheme

    Column(Modifier.padding(horizontal = 16.dp)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .clip(RoundedCornerShape(24.dp))
                .shadow(6.dp, RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = banners[currentIndex]),
                contentDescription = "Banner",
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )

            Box(
                Modifier
                    .matchParentSize()
                    .background(colorScheme.onBackground.copy(alpha = 0.05f))
            )
        }

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(banners.size) { index ->
                val size by animateFloatAsState(if (index == currentIndex) 12f else 7f)
                val color by animateColorAsState(
                    if (index == currentIndex) colorScheme.primary
                    else colorScheme.primary.copy(alpha = 0.3f)
                )

                Box(
                    Modifier
                        .padding(4.dp)
                        .size(size.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
    }
}

/* --------------------------------------------------
   SEARCH BAR
-------------------------------------------------- */
@Composable
fun SearchBarHome() {
    var text by remember { mutableStateOf("") }
    val colorScheme = MaterialTheme.colorScheme

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = { Text("Cari artikel atau informasi...", color = colorScheme.onSurface.copy(alpha = 0.6f)) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorScheme.surface,
            focusedContainerColor = colorScheme.surface,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = colorScheme.primary
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(3.dp, RoundedCornerShape(20.dp))
    )
}

/* --------------------------------------------------
   CATEGORY SECTION
-------------------------------------------------- */
@Composable
fun CategorySection() {
    val categories = listOf(
        CategoryItem("Imunisasi", R.drawable.imunisasi),
        CategoryItem("Artikel", R.drawable.artikel),
        CategoryItem("Klinik", R.drawable.klinik),
        CategoryItem("Edukasi", R.drawable.edukasi),
        CategoryItem("Lokasi", R.drawable.lokasi)
    )
    val colorScheme = MaterialTheme.colorScheme

    Text(
        "Kategori",
        color = colorScheme.onBackground,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(start = 16.dp)
    )

    Spacer(Modifier.height(12.dp))

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        items(categories) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { }
            ) {

                Image(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.title,
                    modifier = Modifier
                        .size(62.dp)
                        .clip(RoundedCornerShape(15.dp))
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    item.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorScheme.onBackground
                )
            }
        }
    }
}

/* --------------------------------------------------
   SECTION TITLE
-------------------------------------------------- */
@Composable
fun SectionTitle(title: String) {
    val colorScheme = MaterialTheme.colorScheme

    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = colorScheme.onBackground,
        modifier = Modifier.padding(start = 16.dp, top = 6.dp)
    )
}

/* --------------------------------------------------
   ARTICLE CARD
-------------------------------------------------- */
@Composable
fun ArticleCard(article: Article, isDark: Boolean, onClick: () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    val shadowColor = if (isDark) Color.White.copy(alpha = 0.2f) else Color.Black.copy(alpha = 0.2f)
    val cardColor = if (isDark) colorScheme.surface.copy(alpha = 0.8f) else colorScheme.surface.copy(alpha = 0.95f)

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(18.dp), ambientColor = shadowColor, spotColor = shadowColor)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {

        Row(
            Modifier
                .padding(18.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = ColorPainter(article.color),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                Text(
                    article.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorScheme.onBackground
                )

                Text(
                    article.desc,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    maxLines = 2,
                    color = colorScheme.onSurface.copy(alpha = 0.7f)
                )

                Text(
                    article.date,
                    fontSize = 12.sp,
                    color = colorScheme.primary
                )
            }
        }
    }
}

/* --------------------------------------------------
   CATEGORY DATA CLASS
-------------------------------------------------- */
data class CategoryItem(
    val title: String,
    val icon: Int
)
