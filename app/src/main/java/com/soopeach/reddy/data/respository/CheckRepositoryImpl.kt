package com.soopeach.reddy.data.respository

import com.soopeach.reddy.data.datasource.CheckDataSource
import com.soopeach.reddy.data.model.request.RequestSave
import com.soopeach.reddy.data.model.response.check.SavedResult
import com.soopeach.reddy.data.model.response.check.CheckedResult
import com.soopeach.reddy.data.model.response.check.SavedResultDetail
import com.soopeach.reddy.domain.respository.CheckRepository

class CheckRepositoryImpl(
    private val checkDataSource: CheckDataSource
): CheckRepository {
    override suspend fun getSavedResults(token: String): List<SavedResult> {
        return checkDataSource.getSavedResults(token)
    }

    override suspend fun checkHowToRecycle(imageUrl: String): CheckedResult {
        return checkDataSource.checkHowToRecycle(imageUrl)
    }

    override suspend fun saveResult(token: String, requestSave: RequestSave): String? {
        return checkDataSource.saveResult(token, requestSave)
    }

    override suspend fun getSavedResultDetail(token: String, groupId: Int): SavedResultDetail {
        return checkDataSource.getSavedResultDetail(token, groupId)
    }
}