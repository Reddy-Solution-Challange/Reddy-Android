package com.soopeach.reddy.data.respository

import com.soopeach.reddy.data.datasource.ArticleDataSource
import com.soopeach.reddy.data.model.Article
import com.soopeach.reddy.data.model.ArticleDetail
import com.soopeach.reddy.data.model.response.EmotionResult
import com.soopeach.reddy.data.model.response.paging.article.ArticlePagingData
import com.soopeach.reddy.domain.respository.ArticleRepository

class ArticleRepositoryImpl(
    private val articleDataSource: ArticleDataSource
) : ArticleRepository {

    override suspend fun getTodayArticles(token: String): List<Article> {
        return articleDataSource.getTodayArticles(token)
    }

    override suspend fun getArticlesTitle(): ArticlePagingData<List<Article>> {
        return articleDataSource.getArticlesTitle()
    }

    override suspend fun getArticleDetail(token: String, id: Int): ArticleDetail {
        return articleDataSource.getArticleDetail(token, id)
    }

    override suspend fun postEmotion(token: String, emotion: String, id: Int): EmotionResult {
        return articleDataSource.postEmotion(token, emotion, id)
    }

}