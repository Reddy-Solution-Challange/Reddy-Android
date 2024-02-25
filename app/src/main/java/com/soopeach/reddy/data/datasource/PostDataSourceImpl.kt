package com.soopeach.reddy.data.datasource

import com.soopeach.reddy.data.model.request.RequestSearchedPost
import com.soopeach.reddy.data.model.response.RecyclingPostDetailResponse
import com.soopeach.reddy.data.model.response.RecyclingPostResponse
import com.soopeach.reddy.data.model.response.paging.recyclingpost.RecyclingPostPagingData
import com.soopeach.reddy.data.service.PostService

class PostDataSourceImpl(
    private val postService: PostService
) : PostDataSource {
    override suspend fun getRecyclingPosts(categoryId: Int) =
        postService.getRecyclingPosts(categoryId).data

    override suspend fun getRecyclingPost(postId: Int): RecyclingPostDetailResponse {
        return postService.getRecyclingPost(postId).data
    }

    override suspend fun getSearchedRecyclingPosts(keyword: String): RecyclingPostPagingData<List<RecyclingPostResponse>> {
        return postService.getSearchedRecyclingPosts(RequestSearchedPost(keyword)).data
    }
}