package com.soopeach.reddy.domain.usecase.article

import com.soopeach.reddy.data.model.response.EmotionResult
import com.soopeach.reddy.domain.respository.ArticleRepository
import com.soopeach.reddy.domain.usecase.GetAccessTokenUseCase

class PostEmotionUseCase(
    private val articleRepository: ArticleRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) {
    suspend operator fun invoke(emotion: String, id: Int): EmotionResult {
        val accessToken = getAccessTokenUseCase()
        return articleRepository.postEmotion(accessToken, emotion, id)
    }
}