package com.soopeach.reddy.domain.usecase

import com.soopeach.reddy.domain.respository.UserRepository

class SignUpUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(googleToken: String): Boolean {
        return userRepository.signUp(googleToken)
    }

}