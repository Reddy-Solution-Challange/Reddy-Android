package com.soopeach.reddy.domain.respository

import com.soopeach.reddy.data.model.response.RecyclingPostDetailResponse
import com.soopeach.reddy.data.model.response.RecyclingPostResponse
import com.soopeach.reddy.data.model.response.paging.recyclingpost.RecyclingPostPagingData

interface PostRepository {

    suspend fun getRecyclingPosts(categoryId: Int): RecyclingPostPagingData<List<RecyclingPostResponse>>

    suspend fun getRecyclingPost(postId: Int): RecyclingPostDetailResponse

    suspend fun getSearchedRecyclingPosts(keyword: String): RecyclingPostPagingData<List<RecyclingPostResponse>>

}