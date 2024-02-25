package com.soopeach.reddy.domain.usecase.camera

import com.soopeach.reddy.domain.respository.CheckRepository
import com.soopeach.reddy.domain.usecase.GetAccessTokenUseCase

class GetSavedResultDetailUseCase(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val checkRepository: CheckRepository
) {
    suspend operator fun invoke(groupId: Int) =
        checkRepository.getSavedResultDetail(getAccessTokenUseCase(), groupId)
}