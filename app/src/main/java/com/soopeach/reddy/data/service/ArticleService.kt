package com.soopeach.reddy.data.service

import com.soopeach.reddy.data.model.Article
import com.soopeach.reddy.data.model.ArticleDetail
import com.soopeach.reddy.data.model.request.RequestEmotion
import com.soopeach.reddy.data.model.response.EmotionResult
import com.soopeach.reddy.data.model.response.Response
import com.soopeach.reddy.data.model.response.paging.article.ArticlePagingResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ArticleService {

    @GET("api/v1/article/today")
    suspend fun getTodayArticles(
        @Header("Authorization") token: String,
    ): Response<List<Article>>

    @GET("api/v1/article")
    suspend fun getArticlesTitle(): ArticlePagingResponse<List<Article>>

    @GET("api/v1/article/{id}")
    suspend fun getArticleDetail(
        @Header("Authorization") token: String,
        @Path("id") id: Int): Response<ArticleDetail>

    @POST("api/v1/article/emotion")
    suspend fun postEmotion(
        @Header("Authorization") token: String,
        @Body requestEmotion: RequestEmotion
    ): Response<EmotionResult>

}