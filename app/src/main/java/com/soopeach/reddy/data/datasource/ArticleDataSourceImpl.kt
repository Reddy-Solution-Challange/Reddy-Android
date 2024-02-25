package com.soopeach.reddy.data.datasource

import com.soopeach.reddy.data.model.Article
import com.soopeach.reddy.data.model.ArticleDetail
import com.soopeach.reddy.data.model.request.RequestEmotion
import com.soopeach.reddy.data.model.response.EmotionResult
import com.soopeach.reddy.data.model.response.paging.article.ArticlePagingData
import com.soopeach.reddy.data.service.ArticleService

class ArticleDataSourceImpl(
    private val articleService: ArticleService
) : ArticleDataSource {
    override suspend fun getTodayArticles(token: String): List<Article> {
        return articleService.getTodayArticles("Bearer $token").data
    }

    override suspend fun getArticlesTitle(): ArticlePagingData<List<Article>> {
        val response = articleService.getArticlesTitle()
        return response.data
    }

    override suspend fun getArticleDetail(token: String, id: Int): ArticleDetail {
        return articleService.getArticleDetail("Bearer $token", id).data
    }

    override suspend fun postEmotion(token: String, emotion: String, id: Int): EmotionResult {
        return articleService.postEmotion("Bearer $token", RequestEmotion(emotion, id)).data
    }
}