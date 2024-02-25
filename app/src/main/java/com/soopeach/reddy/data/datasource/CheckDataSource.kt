package com.soopeach.reddy.data.datasource

import com.soopeach.reddy.data.model.request.RequestSave
import com.soopeach.reddy.data.model.response.check.SavedResult
import com.soopeach.reddy.data.model.response.check.CheckedResult
import com.soopeach.reddy.data.model.response.check.SavedResultDetail

interface CheckDataSource {

    suspend fun getSavedResults(token: String): List<SavedResult>

    suspend fun checkHowToRecycle(imageUrl: String): CheckedResult

    suspend fun saveResult(
        token: String,
        requestSave: RequestSave
    ): String?

    suspend fun getSavedResultDetail(token: String, groupId: Int): SavedResultDetail
}