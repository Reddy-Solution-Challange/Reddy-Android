package com.soopeach.reddy.presentation.ui.screens.save

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.soopeach.reddy.R
import com.soopeach.reddy.data.model.response.check.SavedResultDetail
import com.soopeach.reddy.domain.usecase.article.GetArticleDetailUseCase
import com.soopeach.reddy.domain.usecase.camera.GetSavedResultDetailUseCase
import com.soopeach.reddy.presentation.ui.components.button.ReddyBasicButton
import com.soopeach.reddy.presentation.ui.components.topbar.BackTopBar
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Grey_900
import com.soopeach.reddy.presentation.ui.theme.Reddy_500
import com.soopeach.reddy.presentation.ui.theme.Title2
import com.soopeach.reddy.presentation.ui.theme.White
import org.koin.androidx.compose.get

@Composable
fun SaveDetailScreen(navController: NavController, itemId: Int) {

    val getSavedResultDetailUseCase = get<GetSavedResultDetailUseCase>()
    var savedResultItem by remember {
        mutableStateOf<SavedResultDetail?>(null)
    }

    LaunchedEffect(true) {
        savedResultItem = getSavedResultDetailUseCase(itemId)
    }

    Scaffold(
        topBar = {
            BackTopBar(
                menu = {
                    Icon(
                        painter = painterResource(id = R.drawable.heart_filled),
                        contentDescription = "like icon",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { },
                        tint = Reddy_500
                    )
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            AsyncImage(
                modifier = Modifier
                    .width(185.dp)
                    .height(244.dp)
                    .clip(RoundedCornerShape(14.dp)),
                model = savedResultItem?.imageUrl ?: "https://picsum.photos/200", contentDescription = "image",
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .shadow(3.dp, shape = RoundedCornerShape(14.dp))
                    .background(White)
                    .padding(16.dp),
            ) {

                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = savedResultItem?.resultTitle ?: "Title",
                        style = Title2.copy(Grey_900),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = savedResultItem?.description ?: "Description",
                        style = Body2.copy(Grey_900),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ReddyBasicButton(text = "Take a photo again") {
                navController.navigate("camera") {
                    popUpTo("home")
                }
            }
        }
    }

}