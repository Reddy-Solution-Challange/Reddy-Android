package com.soopeach.reddy.domain.usecase.post

import com.soopeach.reddy.domain.respository.PostRepository

class GetSearchedRecyclingPostsUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(keyword: String) = postRepository.getSearchedRecyclingPosts(keyword)
}