package com.soopeach.reddy.domain.usecase.search

import com.soopeach.reddy.domain.respository.UserRepository

class GetSearchedKeywordsUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): List<String> {
        return userRepository.getSearchedKeywords()
    }
}