package com.soopeach.reddy.presentation.ui.screens.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.soopeach.reddy.R
import com.soopeach.reddy.presentation.ui.components.SearchBarButton
import com.soopeach.reddy.presentation.ui.components.news.NewsItem
import com.soopeach.reddy.presentation.ui.components.topbar.BackTopBar
import com.soopeach.reddy.presentation.ui.components.topbar.LogoTopBar
import com.soopeach.reddy.presentation.ui.theme.Body1
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Caption
import com.soopeach.reddy.presentation.ui.theme.Grey_200
import com.soopeach.reddy.presentation.ui.theme.Grey_400
import com.soopeach.reddy.presentation.ui.theme.Grey_900
import com.soopeach.reddy.presentation.ui.theme.Title1
import com.soopeach.reddy.presentation.ui.theme.White
import com.soopeach.reddy.presentation.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun NewsScreen(navController: NavController) {

    val viewModel = koinViewModel<NewsViewModel>()
    val state by viewModel.collectAsState()

    LazyColumn(
        Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ) {

        item {
            Spacer(modifier = Modifier.height(56.dp))
        }

        item {
            Text(
                text = "Today's News",
                style = Title1.copy(Grey_900)
            )
        }

        item {
            Spacer(modifier = Modifier.height(18.dp))
        }

        item {
            state.todayNews.getDataOrNull()?.let { article ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .clickable {
                            navController.navigate("newsDetail/${article.id}")
                        },
                    contentAlignment = Alignment.BottomCenter
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        model = article.imageUrl, contentDescription = "today's news",
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Grey_200.copy(alpha = 0.2f))
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        text = article.title,
                        style = Body2.copy(White)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            Text(
                text = "Recommended News",
                style = Title1.copy(Grey_900)
            )
        }

        item {
            Spacer(modifier = Modifier.height(14.dp))

        }
        item {
            state.recommendedNews.getDataOrNull()?.let { articles ->
                articles.forEach {
                    NewsItem(
                        imageUrl = it.imageUrl,
                        content = it.title,
                        commentCount = 10
                    ) {
                        navController.navigate("newsDetail/${it.id}")
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                }

            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}