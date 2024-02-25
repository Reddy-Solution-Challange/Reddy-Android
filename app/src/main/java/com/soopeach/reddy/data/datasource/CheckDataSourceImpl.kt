package com.soopeach.reddy.data.datasource

import com.soopeach.reddy.data.model.request.RequestCheck
import com.soopeach.reddy.data.model.request.RequestSave
import com.soopeach.reddy.data.model.response.check.SavedResult
import com.soopeach.reddy.data.model.response.check.CheckedResult
import com.soopeach.reddy.data.model.response.check.SavedResultDetail
import com.soopeach.reddy.data.service.CheckService

class CheckDataSourceImpl(
    private val checkService: CheckService
) : CheckDataSource {

    override suspend fun getSavedResults(token: String): List<SavedResult> {
        return checkService.getSavedResults("Bearer $token").data
    }

    override suspend fun checkHowToRecycle(
        imageUrl: String
    ): CheckedResult {
        return checkService.requestCheck(RequestCheck(imageUrl)).data
    }

    override suspend fun saveResult(token: String, requestSave: RequestSave): String? {
        val response = checkService.saveResult("Bearer $token", requestSave).data
        return response
    }

    override suspend fun getSavedResultDetail(token: String, groupId: Int): SavedResultDetail {
        return checkService.getSavedResultDetail("Bearer $token", groupId).data
    }


}