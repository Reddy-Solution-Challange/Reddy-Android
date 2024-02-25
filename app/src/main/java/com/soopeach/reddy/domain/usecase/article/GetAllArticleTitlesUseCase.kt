package com.soopeach.reddy.domain.usecase.article

import com.soopeach.reddy.domain.respository.ArticleRepository

class GetAllArticleTitlesUseCase(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke() = articleRepository.getArticlesTitle()
}