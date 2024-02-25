package com.soopeach.reddy.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.soopeach.reddy.domain.EMPTY
import com.soopeach.reddy.presentation.Screen.Companion.bottomNavigationItems
import com.soopeach.reddy.presentation.ui.screens.camera.CameraScreen
import com.soopeach.reddy.presentation.ui.screens.camera.CheckScreen
import com.soopeach.reddy.presentation.ui.screens.camera.ResultScreen
import com.soopeach.reddy.presentation.ui.screens.home.HomeScreen
import com.soopeach.reddy.presentation.ui.screens.mypage.MyPageScreen
import com.soopeach.reddy.presentation.ui.screens.news.NewsScreen
import com.soopeach.reddy.presentation.ui.screens.home.CategoryDetailScreen
import com.soopeach.reddy.presentation.ui.screens.home.CategoryScreen
import com.soopeach.reddy.presentation.ui.screens.home.SearchResultScreen
import com.soopeach.reddy.presentation.ui.screens.save.SaveScreen
import com.soopeach.reddy.presentation.ui.screens.home.SearchScreen
import com.soopeach.reddy.presentation.ui.screens.news.NewsDetailScreen
import com.soopeach.reddy.presentation.ui.screens.save.SaveDetailScreen
import com.soopeach.reddy.presentation.ui.theme.Grey_200
import com.soopeach.reddy.presentation.ui.theme.Grey_400
import com.soopeach.reddy.presentation.ui.theme.Reddy_400
import com.soopeach.reddy.presentation.ui.theme.White

@Composable
fun AppScreen(
    onSignOutClicked: () -> Unit,
) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            AnimatedVisibility(
                visible = (currentDestination?.route in bottomNavigationItems.map { it.route })
                        && currentDestination?.route != Screen.CAMERA.route,
                enter = slideInVertically(initialOffsetY = {
                    it
                }),
                exit = slideOutVertically(targetOffsetY = {
                    it
                })
            )
            {
                BottomNavigation(
                    Modifier.height(100.dp),
                    elevation = 10.dp,
                    backgroundColor = White,
                ) {
                    bottomNavigationItems.forEach { screen ->

                        val selected =
                            currentDestination?.hierarchy?.any { it.route == screen.route } == true

                        Column(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                                .padding(top = 10.dp)
                                .background(White)
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {

                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    if (selected) requireNotNull(screen.selectedIcon) else requireNotNull(
                                        screen.unSelectedIcon
                                    )
                                ),
                                tint = if (selected) Reddy_400 else Grey_400,
                                contentDescription = null,
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                stringResource(requireNotNull(screen.stringResourceId)),
                                color = if (selected) Reddy_400 else Grey_400,
                            )
                        }
                    }
                }
            }

        },
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.NEWS.route,
            Modifier.padding(innerPadding),
        ) {
            composable(Screen.NEWS.route) {
                NewsScreen(navController)
            }

            composable(
                Screen.NEWS_DETAIL.route,
                Screen.NEWS_DETAIL.arguments,
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val articleId = arguments.getLong("articleId")
                NewsDetailScreen(navController, articleId)
            }

            composable(Screen.HOME.route) {
                HomeScreen(navController)
            }

            composable(
                Screen.CATEGORY.route,
                Screen.CATEGORY.arguments,
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val categoryId = arguments.getInt("categoryId")
                CategoryScreen(navController, categoryId)
            }

            composable(
                Screen.CATEGORY_DETAIL.route,
                Screen.CATEGORY_DETAIL.arguments,
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val categoryId = arguments.getInt("categoryId")
                CategoryDetailScreen(navController, categoryId)
            }

            composable(Screen.SEARCH.route) {
                SearchScreen(navController)
            }

            composable(
                Screen.SEARCH_RESULT.route,
                Screen.SEARCH_RESULT.arguments,
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val keyword = arguments.getString("keyword") ?: String.EMPTY
                SearchResultScreen(navController, keyword)
            }

            composable(Screen.CAMERA.route) {
                CameraScreen(navController)
            }

            composable(
                Screen.CheckScreen.route,
                Screen.CheckScreen.arguments,
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val imageUri = arguments.getString("imageUri") ?: String.EMPTY
                CheckScreen(navController, imageUri)
            }

            composable(Screen.Result.route) {
                ResultScreen(navController)
            }

            composable(Screen.SAVE.route) {
                SaveScreen(navController)
            }

            composable(
                Screen.SAVE_DETAIL.route,
                Screen.SAVE_DETAIL.arguments,
            ) { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val itemId = arguments.getInt("itemId")
                SaveDetailScreen(navController, itemId)
            }

            composable(Screen.MYPAGE.route) {
                MyPageScreen(navController, onSignOutClicked)
            }
        }
    }
}