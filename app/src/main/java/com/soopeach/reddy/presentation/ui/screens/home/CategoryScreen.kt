package com.soopeach.reddy.presentation.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.soopeach.reddy.data.model.response.RecyclingPostResponse
import com.soopeach.reddy.domain.model.RecycleItem
import com.soopeach.reddy.domain.usecase.post.GetRecyclingPostsUseCase
import com.soopeach.reddy.presentation.ui.components.PostItem
import com.soopeach.reddy.presentation.ui.components.topbar.BackTopBarWithTitle
import org.koin.androidx.compose.get

@Composable
fun CategoryScreen(navController: NavController, categoryId: Int) {

    val category = RecycleItem.entries.find { it.id == categoryId } ?: RecycleItem.PAPER

    val getRecyclingPostsUseCase = get<GetRecyclingPostsUseCase>()
    var posts by remember {
        mutableStateOf<List<RecyclingPostResponse>>(emptyList())
    }

    LaunchedEffect(true) {
        posts = getRecyclingPostsUseCase(categoryId).separatePosts
    }

    Scaffold(
        topBar = {
            BackTopBarWithTitle(title = category.name) {
                navController.popBackStack()
            }

        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            posts.forEach {
                item {
                    Column(
                        Modifier.padding(horizontal = 20.dp).fillMaxWidth()
                    ) {
                        PostItem(
                            imageUrl = it.imageUrl,
                            content = it.title
                        ) {
                            navController.navigate("categoryDetail/${it.id}")
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}