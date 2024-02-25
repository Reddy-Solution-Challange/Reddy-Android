package com.soopeach.reddy.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.soopeach.reddy.R

sealed class Screen(
    val route: String,
    @StringRes val stringResourceId: Int? = null,
    @DrawableRes val unSelectedIcon: Int? = null,
    @DrawableRes val selectedIcon: Int? = null,
    val arguments: List<NamedNavArgument> = emptyList(),
) {

    data object NEWS : Screen(
        route = "news",
        stringResourceId = R.string.news,
        unSelectedIcon = R.drawable.news,
        selectedIcon = R.drawable.selected_news,
    )

    data object NEWS_DETAIL : Screen(
        route = "newsDetail/{articleId}",
        arguments = listOf(
            navArgument("articleId") { type = NavType.LongType },
        ),
    )

    data object HOME : Screen(
        route = "home",
        stringResourceId = R.string.home,
        unSelectedIcon = R.drawable.home,
        selectedIcon = R.drawable.selected_home,
    )

    data object CATEGORY : Screen(
        route = "category/{categoryId}",
        arguments = listOf(
            navArgument("categoryId") { type = NavType.IntType },
        ),
    )

    data object CATEGORY_DETAIL : Screen(
        route = "categoryDetail/{categoryId}",
        arguments = listOf(
            navArgument("categoryId") { type = NavType.IntType },
        ),
    )

    data object SEARCH : Screen(
        route = "search",
    )

    data object SEARCH_RESULT : Screen(
        route = "searchResult/{keyword}",
        arguments = listOf(
            navArgument("keyword") { type = NavType.StringType },
        ),
    )

    data object CAMERA : Screen(
        route = "camera",
        stringResourceId = R.string.camera,
        unSelectedIcon = R.drawable.camera,
        selectedIcon = R.drawable.selected_camera,
    )

    data object CheckScreen : Screen(
        route = "check/{imageUri}",
        arguments = listOf(
            navArgument("imageUri") { type = NavType.StringType },
        ),
    )

    data object Result : Screen(
        route = "result",
    )

    data object SAVE: Screen(
        route = "save",
        stringResourceId = R.string.save,
        unSelectedIcon = R.drawable.save,
        selectedIcon = R.drawable.selected_save,
    )

    data object SAVE_DETAIL : Screen(
        route = "saveDetail/{itemId}",
        arguments = listOf(
            navArgument("itemId") { type = NavType.IntType },
        ),
    )

    data object MYPAGE : Screen(
        route = "myPage",
        stringResourceId = R.string.my,
        unSelectedIcon = R.drawable.my,
        selectedIcon = R.drawable.selected_my,
    )

    companion object {
        val bottomNavigationItems = listOf(NEWS, HOME, CAMERA, SAVE, MYPAGE)
    }
}