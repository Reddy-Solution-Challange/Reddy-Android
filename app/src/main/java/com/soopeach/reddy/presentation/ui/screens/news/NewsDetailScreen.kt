package com.soopeach.reddy.presentation.ui.screens.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.soopeach.reddy.R
import com.soopeach.reddy.data.model.Article
import com.soopeach.reddy.data.model.ArticleDetail
import com.soopeach.reddy.domain.model.Reactions
import com.soopeach.reddy.domain.usecase.article.GetArticleDetailUseCase
import com.soopeach.reddy.domain.usecase.article.PostEmotionUseCase
import com.soopeach.reddy.presentation.ui.components.ReactionItem
import com.soopeach.reddy.presentation.ui.components.topbar.BackTopBarWithTitle
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Body3
import com.soopeach.reddy.presentation.ui.theme.Caption
import com.soopeach.reddy.presentation.ui.theme.Grey_200
import com.soopeach.reddy.presentation.ui.theme.Grey_400
import com.soopeach.reddy.presentation.ui.theme.Grey_900
import com.soopeach.reddy.presentation.ui.theme.Title1
import com.soopeach.reddy.presentation.ui.theme.Title3
import com.soopeach.reddy.presentation.ui.theme.White
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun NewsDetailScreen(navController: NavController, articleId: Long) {

    val getArticleDetailUseCase = get<GetArticleDetailUseCase>()
    val postEmotionUseCase = get<PostEmotionUseCase>()

    var articleState by remember {
        mutableStateOf<ArticleDetail?>(null)
    }

    var selectedReaction by remember {
        mutableStateOf<Reactions?>(null)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        articleState = getArticleDetailUseCase(articleId.toInt())
    }

    LaunchedEffect(articleState) {
        selectedReaction = when {
            articleState?.isSosoEmotion == true -> Reactions.SOSO
            articleState?.isGoodEmotion == true -> Reactions.WOW
            articleState?.isAnalysisEmotion == true -> Reactions.BAD
            articleState?.isEmpathyEmotion == true -> Reactions.GOOD
            else -> null
        }
    }

    Scaffold(
        topBar = {
            BackTopBarWithTitle(title = "") {
                navController.popBackStack()
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                        .background(White)
                        .padding(20.dp)
                ) {

                    Spacer(modifier = Modifier.height(26.dp))

                    Text(
                        text = articleState?.title ?: "Title",
                        maxLines = 1,
                        style = Title1.copy(Grey_900)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = articleState?.date ?: "0000.00.00",
                        style = Caption.copy(Grey_400)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = articleState?.contents ?: "Loading...",
                        style = Body2
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Grey_200)
                    ) {}
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(White)
                        .padding(20.dp)
                ) {
                    Text(text = "How was it?", style = Title3.copy(Grey_900))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 38.dp, vertical = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Reactions.entries.forEach {
                            ReactionItem(it, it == selectedReaction) {
                                scope.launch {
                                    val newEmotionInfo =
                                        postEmotionUseCase(it.textForRequest, articleId.toInt())
                                    articleState = articleState?.copy(
                                        isSosoEmotion = newEmotionInfo.isSosoEmotion,
                                        isGoodEmotion = newEmotionInfo.isGoodEmotion,
                                        isAnalysisEmotion = newEmotionInfo.isAnalysisEmotion,
                                        isEmpathyEmotion = newEmotionInfo.isEmpathyEmotion
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )
            }

        }
    }
}