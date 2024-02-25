package com.soopeach.reddy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.soopeach.reddy.data.model.Article
import com.soopeach.reddy.domain.usecase.article.GetAllArticleTitlesUseCase
import com.soopeach.reddy.domain.usecase.article.GetTodayArticlesUseCase
import com.soopeach.reddy.presentation.model.UiState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

sealed interface NewsSideEffect {
}

data class NewsState(
    val todayNews: UiState<Article> = UiState.Idle,
    val recommendedNews: UiState<List<Article>> = UiState.Idle
)

class NewsViewModel(
    private val getAllArticleTitlesUseCase: GetAllArticleTitlesUseCase,
    private val getTodayArticlesUseCase: GetTodayArticlesUseCase
) : ViewModel(), ContainerHost<NewsState, NewsSideEffect> {

    override val container: Container<NewsState, NewsSideEffect> = container(NewsState())

    init {
        getTodayNews()
        getRecommendedNews()
    }

    private fun getTodayNews() = intent {
        val response = getTodayArticlesUseCase()
        reduce { state.copy(todayNews = UiState.Success(response.shuffled().first())) }
    }

    private fun getRecommendedNews() = intent {
        val response = getAllArticleTitlesUseCase()
        val news = response.articles
        reduce { state.copy(recommendedNews = UiState.Success(news)) }
    }

}