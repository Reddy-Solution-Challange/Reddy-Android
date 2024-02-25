package com.soopeach.reddy.domain.usecase.camera

import com.soopeach.reddy.domain.respository.CheckRepository
import com.soopeach.reddy.domain.usecase.GetAccessTokenUseCase

class GetSavedResultsUseCase(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val checkRepository: CheckRepository
) {
    suspend operator fun invoke() = checkRepository.getSavedResults(getAccessTokenUseCase())
}