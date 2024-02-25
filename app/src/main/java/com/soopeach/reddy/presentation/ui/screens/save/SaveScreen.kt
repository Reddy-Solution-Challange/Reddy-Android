package com.soopeach.reddy.presentation.ui.screens.save

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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
import com.soopeach.reddy.data.model.response.check.SavedResult
import com.soopeach.reddy.domain.usecase.camera.GetSavedResultsUseCase
import com.soopeach.reddy.presentation.ui.components.SavedItem
import com.soopeach.reddy.presentation.ui.components.topbar.LogoTopBar
import com.soopeach.reddy.presentation.ui.theme.Grey_200
import org.koin.androidx.compose.get

@Composable
fun SaveScreen(navController: NavController) {

    val getSavedResultsUseCase = get<GetSavedResultsUseCase>()

    var posts by remember {
        mutableStateOf<List<SavedResult>>(emptyList())
    }

    LaunchedEffect(true) {
        posts = getSavedResultsUseCase()
    }

    Scaffold(
        topBar = {
            LogoTopBar()
        },
        containerColor = Grey_200
    ) { innerPadding ->

        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(innerPadding),
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            verticalItemSpacing = 12.dp,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            posts.forEach {
                item {
                    SavedItem(
                        imageUrl = it.imageUrl, content = it.resultTitle
                    ) {
                        navController.navigate("saveDetail/${it.groupId}")
                    }
                }
            }
        }

    }
}