package com.soopeach.reddy.domain.usecase.search

import com.soopeach.reddy.domain.respository.UserRepository

class UpdateSearchedKeywordUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(keyword: String): List<String> {
        return userRepository.updateSearchedKeyword(keyword)
    }
}