package com.soopeach.reddy.presentation.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.soopeach.reddy.R
import com.soopeach.reddy.data.model.Article
import com.soopeach.reddy.domain.model.RecycleItem
import com.soopeach.reddy.domain.usecase.article.GetAllArticleTitlesUseCase
import com.soopeach.reddy.domain.usecase.article.GetArticleDetailUseCase
import com.soopeach.reddy.presentation.Screen
import com.soopeach.reddy.presentation.ui.components.CategoryItem
import com.soopeach.reddy.presentation.ui.components.topbar.LogoTopBar
import com.soopeach.reddy.presentation.ui.components.news.SquareNewsItem
import com.soopeach.reddy.presentation.ui.components.SearchBarButton
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Body3
import com.soopeach.reddy.presentation.ui.theme.Grey_400
import com.soopeach.reddy.presentation.ui.theme.Heading3
import com.soopeach.reddy.presentation.ui.theme.Reddy_500
import com.soopeach.reddy.presentation.ui.theme.Title1
import com.soopeach.reddy.presentation.ui.theme.Title2
import com.soopeach.reddy.presentation.ui.theme.White
import org.koin.androidx.compose.get

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController) {

    val getAllArticleTitlesUseCase = get<GetAllArticleTitlesUseCase>()

    var articlesState by remember {
        mutableStateOf<List<Article>>(emptyList())
    }

    LaunchedEffect(true) {
        val response = getAllArticleTitlesUseCase()
        articlesState = response.articles
    }

    Scaffold(
        topBar = {
            LogoTopBar(false)
        }
    ) { innerPadding ->

        val scrollState = rememberLazyListState()
        LazyColumn(
            Modifier
                .padding(innerPadding),
            state = scrollState
        ) {

            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .shadow(
                            if (0 < scrollState.firstVisibleItemIndex) scrollState.firstVisibleItemIndex.dp * 3 else 0.dp
                        )
                        .background(
                            White.copy(
                                if (0 < scrollState.firstVisibleItemIndex) 1f else 0.6f
                            )
                        ),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Grey_400,
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(24.dp)
                            .clickable {
                                navController.navigate(Screen.SEARCH.route)
                            }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(18.dp))
            }

            item {

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(275.dp)
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Column(
                            modifier = Modifier,
                        ) {
                            Text(text = "Recycling", style = Heading3.copy(Reddy_500))
                            Text(text = "isn't difficult", style = Heading3)
                            Text(text = "at all", style = Heading3)

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier
                                    .height(40.dp)
                                    .clip(RoundedCornerShape(100.dp))
                                    .background(Reddy_500)
                                    .clickable {
                                        navController.navigate("camera")
                                    }
                                    .padding(horizontal = 14.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.camera),
                                    contentDescription = "camera icon",
                                    tint = White,
                                )

                                Spacer(modifier = Modifier.width(5.dp))

                                Text(
                                    modifier = Modifier,
                                    text = "Camera",
                                    style = Body2.copy(White)
                                )
                            }

                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        Alignment.BottomEnd
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.5f)
                                .padding(bottom = 60.dp),
                            painter = painterResource(id = R.drawable.ch_main),
                            contentDescription = "main character"
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        RecycleItem.entries.forEach { recycleItem ->
                            CategoryItem(recycleItem = recycleItem) {
                                navController.navigate("category/${recycleItem.id}")
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(69.dp))
            }

            item {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "Suggested News", style = Title2
                )
            }

            item {
                Spacer(modifier = Modifier.height(19.dp))
            }

            if (articlesState.isNotEmpty()) {
                repeat(5) { index ->
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .padding(bottom = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(modifier = Modifier.weight(1f)) {
                                SquareNewsItem(
                                    imageUrl = articlesState[index].imageUrl,
                                    content = articlesState[index].title
                                )
                            }

                            Box(modifier = Modifier.weight(1f)) {
                                SquareNewsItem(
                                    imageUrl = articlesState[index + 5].imageUrl,
                                    content = articlesState[index + 5].title
                                )
                            }

                        }
                    }
                }
            }


            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }


}

@Composable
fun LegacyHomeScreen(navController: NavController) {

    Scaffold(
        topBar = {
            LogoTopBar()
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(top = 32.dp, start = 20.dp, end = 20.dp)
        ) {

            SearchBarButton {
                navController.navigate(Screen.SEARCH.route)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RecycleItem.entries.forEach { recycleItem ->
                    CategoryItem(recycleItem = recycleItem) {

                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .clickable {

                    },
                elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF03E6F1), Color(0xFF02F7B9)),
                            ),
                        )
                        .fillMaxWidth()
                        .height(128.dp),
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                            .padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "내 쓰레기", style = Title1.copy(White))
                        Text(text = "분리수거가 가능한가?", style = Title1.copy(White))
                    }

                    Spacer(modifier = Modifier.height(19.dp))

                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 15.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(Color(0x3300302A))
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 1.dp),
                            text = "확인하러가기", style = Body3.copy(color = White)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(56.dp))

            Text(text = "Reddy의 추천 뉴스", style = Title2)

            Spacer(modifier = Modifier.height(19.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                repeat(10) {
                    item {
                        SquareNewsItem(
                            imageUrl = "https://picsum.photos/300",
                            content = "니가\n뭔데"
                        )
                    }
                }
            }
        }
    }

}