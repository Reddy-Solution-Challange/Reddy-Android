package com.soopeach.reddy.domain.usecase.camera

import com.soopeach.reddy.domain.respository.CheckRepository

class CheckHowToRecycleUseCase(
    private val checkRepository: CheckRepository
) {
    suspend fun checkHowToRecycle(imageUrl: String) = checkRepository.checkHowToRecycle(imageUrl)
}