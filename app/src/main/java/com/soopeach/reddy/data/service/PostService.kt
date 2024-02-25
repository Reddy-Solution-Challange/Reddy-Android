package com.soopeach.reddy.data.service

import com.soopeach.reddy.data.model.request.RequestSearchedPost
import com.soopeach.reddy.data.model.response.RecyclingPostDetailResponse
import com.soopeach.reddy.data.model.response.RecyclingPostResponse
import com.soopeach.reddy.data.model.response.Response
import com.soopeach.reddy.data.model.response.paging.recyclingpost.RecyclingPostPagingResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @GET("api/v1/separate")
    suspend fun getRecyclingPosts(
        @Query("category") categoryId: Int,
    ): RecyclingPostPagingResponse<List<RecyclingPostResponse>>

    @GET("api/v1/separate/{id}")
    suspend fun getRecyclingPost(
        @Path("id") postId: Int,
    ): Response<RecyclingPostDetailResponse>

    @POST("api/v1/separate/search")
    suspend fun getSearchedRecyclingPosts(
        @Body searchRequest: RequestSearchedPost
    ): RecyclingPostPagingResponse<List<RecyclingPostResponse>>



}