package com.soopeach.reddy.domain.usecase

import com.soopeach.reddy.domain.respository.UserRepository

class GetAccessTokenUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.getAccessToken()
}