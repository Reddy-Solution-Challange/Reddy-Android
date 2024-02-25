package com.soopeach.reddy.presentation.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.soopeach.reddy.R
import com.soopeach.reddy.data.model.response.RecyclingPostResponse
import com.soopeach.reddy.domain.usecase.post.GetSearchedRecyclingPostsUseCase
import com.soopeach.reddy.presentation.ui.components.PostItem
import com.soopeach.reddy.presentation.ui.components.topbar.BackTopBarWithTitle
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Grey_200
import com.soopeach.reddy.presentation.ui.theme.Grey_400
import com.soopeach.reddy.presentation.ui.theme.White
import org.koin.androidx.compose.get

@Composable
fun SearchResultScreen(navController: NavController, keyword: String) {

    val getSearchedRecyclingPostsUseCase = get<GetSearchedRecyclingPostsUseCase>()

    var posts by remember {
        mutableStateOf<List<RecyclingPostResponse>?>(null)
    }

    LaunchedEffect(true) {
        posts = getSearchedRecyclingPostsUseCase(keyword).separatePosts
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .background(White)
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .clip(shape = RoundedCornerShape(100.dp))
                        .background(Grey_200)
                        .weight(1f)
                        .padding(10.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = keyword,
                        style = Body2.copy(
                            color = Grey_400
                        ),
                    )

                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        },
                    text = "Cancel",
                    style = Body2.copy(color = Grey_400)
                )

            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            posts?.let {
                items(it.size) { index ->
                    Column(
                        Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                    ) {
                        PostItem(imageUrl = it[index].imageUrl, content = it[index].title) {
                            navController.navigate("categoryDetail/${it[index].id}")
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }

}