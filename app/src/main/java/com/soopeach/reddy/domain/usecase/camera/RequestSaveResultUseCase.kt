package com.soopeach.reddy.domain.usecase.camera

import com.soopeach.reddy.data.model.request.RequestSave
import com.soopeach.reddy.domain.respository.CheckRepository
import com.soopeach.reddy.domain.usecase.GetAccessTokenUseCase

class RequestSaveResultUseCase(
    private val checkRepository: CheckRepository,
    private val getAccessTokenUseCase: GetAccessTokenUseCase
) {
    suspend operator fun invoke(
        imageUrl: String,
        resultId: Int,
    ): String? {
        val token = getAccessTokenUseCase()
        return checkRepository.saveResult(
            token, RequestSave(
                imageUrl = imageUrl,
                resultId = resultId,
                groupId = 0
            )
        )
    }
}
