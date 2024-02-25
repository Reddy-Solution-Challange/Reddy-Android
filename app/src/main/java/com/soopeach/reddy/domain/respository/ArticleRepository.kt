package com.soopeach.reddy.domain.respository

import com.soopeach.reddy.data.model.Article
import com.soopeach.reddy.data.model.ArticleDetail
import com.soopeach.reddy.data.model.response.EmotionResult
import com.soopeach.reddy.data.model.response.paging.article.ArticlePagingData

interface ArticleRepository {

    suspend fun getTodayArticles(token: String): List<Article>

    suspend fun getArticlesTitle(): ArticlePagingData<List<Article>>

    suspend fun getArticleDetail(token: String, id: Int): ArticleDetail

    suspend fun postEmotion(token: String, emotion: String, id: Int): EmotionResult
}