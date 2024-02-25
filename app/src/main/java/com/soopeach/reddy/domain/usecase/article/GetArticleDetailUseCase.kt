package com.soopeach.reddy.domain.usecase.article

import com.soopeach.reddy.data.model.ArticleDetail
import com.soopeach.reddy.domain.respository.ArticleRepository
import com.soopeach.reddy.domain.usecase.GetAccessTokenUseCase

class GetArticleDetailUseCase(
    private val articleRepository: ArticleRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) {
    suspend operator fun invoke(id: Int): ArticleDetail {
        val accessToken = getAccessTokenUseCase()
        return articleRepository.getArticleDetail(accessToken, id)
    }
}