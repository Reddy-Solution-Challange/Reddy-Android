package com.soopeach.reddy.domain.usecase.post

import com.soopeach.reddy.domain.respository.PostRepository

class GetRecyclingPostUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(postId: Int) = postRepository.getRecyclingPost(postId)
}