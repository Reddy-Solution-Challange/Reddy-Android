package com.soopeach.reddy.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.soopeach.reddy.data.model.response.RecyclingPostDetailResponse
import com.soopeach.reddy.data.model.response.RecyclingPostResponse
import com.soopeach.reddy.domain.model.RecycleItem
import com.soopeach.reddy.domain.usecase.post.GetRecyclingPostUseCase
import com.soopeach.reddy.domain.usecase.post.GetRecyclingPostsUseCase
import com.soopeach.reddy.presentation.ui.components.PostItem
import com.soopeach.reddy.presentation.ui.components.topbar.BackTopBarWithTitle
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Title3
import com.soopeach.reddy.presentation.ui.theme.White
import org.koin.androidx.compose.get

@Composable
fun CategoryDetailScreen(navController: NavController, postId: Int) {

    val getRecyclingPostUseCase = get<GetRecyclingPostUseCase>()
    var post by remember {
        mutableStateOf<RecyclingPostDetailResponse?>(null)
    }

    LaunchedEffect(true) {
        post = getRecyclingPostUseCase(postId)
    }

    Scaffold(
        topBar = {
            BackTopBarWithTitle(title = "") {
                navController.popBackStack()
            }

        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = androidx.compose.ui.Alignment.TopCenter
        ) {

            AsyncImage(
                model = post?.imageUrl ?: "https://picsum.photos/300",
                contentDescription = "post image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(top = 190.dp)
                    .shadow(5.dp, RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                    .background(White)
                    .padding(top = 32.dp)
                    .padding(horizontal = 21.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = post?.title ?: "Title",
                    style = Title3,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = post?.description ?: "Description",
                    style = Body2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}