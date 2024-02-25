package com.soopeach.reddy.data.respository

import com.soopeach.reddy.data.datasource.PostDataSource
import com.soopeach.reddy.data.model.response.RecyclingPostDetailResponse
import com.soopeach.reddy.data.model.response.RecyclingPostResponse
import com.soopeach.reddy.data.model.response.paging.recyclingpost.RecyclingPostPagingData
import com.soopeach.reddy.domain.respository.PostRepository

class PostRepositoryImpl(
    private val postDataSource: PostDataSource
) : PostRepository {
    override suspend fun getRecyclingPosts(categoryId: Int): RecyclingPostPagingData<List<RecyclingPostResponse>> {
        return postDataSource.getRecyclingPosts(categoryId)
    }

    override suspend fun getRecyclingPost(postId: Int): RecyclingPostDetailResponse {
        return postDataSource.getRecyclingPost(postId)
    }

    override suspend fun getSearchedRecyclingPosts(keyword: String): RecyclingPostPagingData<List<RecyclingPostResponse>> {
        return postDataSource.getSearchedRecyclingPosts(keyword)
    }
}