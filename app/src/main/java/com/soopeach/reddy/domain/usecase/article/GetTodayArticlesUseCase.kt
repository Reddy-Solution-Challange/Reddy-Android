package com.soopeach.reddy.domain.usecase.article

import com.soopeach.reddy.domain.respository.ArticleRepository
import com.soopeach.reddy.domain.usecase.GetAccessTokenUseCase

class GetTodayArticlesUseCase(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val articleRepository: ArticleRepository
) {

    suspend operator fun invoke() = articleRepository.getTodayArticles(getAccessTokenUseCase())
}