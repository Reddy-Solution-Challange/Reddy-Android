package com.soopeach.reddy.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.soopeach.reddy.domain.usecase.post.GetSearchedRecyclingPostsUseCase
import com.soopeach.reddy.domain.usecase.search.DeleteSearchedKeywordUseCase
import com.soopeach.reddy.domain.usecase.search.GetSearchedKeywordsUseCase
import com.soopeach.reddy.domain.usecase.search.UpdateSearchedKeywordUseCase
import com.soopeach.reddy.presentation.ui.components.RecentlySearchedItem
import com.soopeach.reddy.presentation.ui.components.topbar.BackTopBar
import com.soopeach.reddy.presentation.ui.theme.Body2
import com.soopeach.reddy.presentation.ui.theme.Body3
import com.soopeach.reddy.presentation.ui.theme.Grey_200
import com.soopeach.reddy.presentation.ui.theme.Grey_400
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun SearchScreen(navController: NavController) {

    val focusManager = LocalFocusManager.current

    val getSearchedKeywordsUseCase = get<GetSearchedKeywordsUseCase>()
    val updateSearchedKeywordUseCase = get<UpdateSearchedKeywordUseCase>()
    val deleteSearchedKeywordUseCase = get<DeleteSearchedKeywordUseCase>()

    var searchedKeywords by remember { mutableStateOf(listOf<String>()) }

    val scope = rememberCoroutineScope()

    var inputText by remember { mutableStateOf("") }

    LaunchedEffect(true) {
        searchedKeywords = getSearchedKeywordsUseCase()
    }

    Scaffold(
        topBar = {
            BackTopBar(
                hasElevation = false,
                onBackClicked = {
                    navController.popBackStack()
                },
                menu = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            Modifier
                                .padding(8.dp)
                                .fillMaxHeight()
                                .clip(shape = RoundedCornerShape(100.dp))
                                .background(Grey_200)
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            BasicTextField(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth(),
                                value = inputText,
                                onValueChange = {
                                    inputText = it
                                },
                                textStyle = Body2,
                                singleLine = true,
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        scope.launch {
                                            focusManager.clearFocus()
                                            val targetKeyword = inputText
                                            inputText = ""
                                            searchedKeywords = updateSearchedKeywordUseCase(targetKeyword)
                                            navController.navigate("searchResult/$targetKeyword")
                                        }
                                    }

                                ),
                            ) { innerTextField ->
                                innerTextField()

                                if (inputText.isEmpty()) {
                                    Text(
                                        text = "Wanna know how to recycle?",
                                        style = Body3.copy(
                                            color = Grey_400
                                        ),
                                    )
                                }
                            }

                        }

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            modifier = Modifier
                                .clickable {
                                    inputText = ""
                                    focusManager.clearFocus()
                                },
                            text = "Cancel",
                            style = Body2.copy(color = Grey_400)
                        )

                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 16.dp)
                .padding(horizontal = 20.dp)
        ) {

            Text(text = "Recently Searched", style = Body2.copy(Grey_400))

            Spacer(modifier = Modifier.height(16.dp))

            searchedKeywords.reversed().forEach {
                RecentlySearchedItem(it, onClicked = {

                }) {
                    scope.launch {
                        searchedKeywords = deleteSearchedKeywordUseCase(it)
                    }

                }
            }

        }
    }


}