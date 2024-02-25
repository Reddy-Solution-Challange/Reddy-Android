package com.soopeach.reddy.domain.usecase.post

import com.soopeach.reddy.domain.respository.PostRepository

class GetRecyclingPostsUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(categoryId: Int) = postRepository.getRecyclingPosts(categoryId)
}