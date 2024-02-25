package com.soopeach.reddy.data.datasource

import com.soopeach.reddy.data.LocalHistoryDataStore
import com.soopeach.reddy.data.UserLocalDataStore
import com.soopeach.reddy.data.model.request.RequestToken
import com.soopeach.reddy.data.service.UserService

class UserDataSourceImpl(
    private val userLocalDataStore: UserLocalDataStore,
    private val historyDataStore: LocalHistoryDataStore,
    private val userService: UserService
): UserDataSource {

    override suspend fun signUp(googleToken: String): Boolean {
        val response = userService.requestSignIn(RequestToken(googleToken))
        userLocalDataStore.updateToken(response.data)
        return true
    }

    override suspend fun getAccessToken(): String {
        return requireNotNull(userLocalDataStore.getAccessToken())
    }

    override suspend fun getSearchedKeywords(): List<String> {
        return historyDataStore.getSearchedKeyword()
    }

    override suspend fun updateSearchedKeyword(newKeyword: String): List<String> {
        return historyDataStore.updateSearchedKeyword(newKeyword)
    }

    override suspend fun deleteSearchedKeyword(targetKeyword: String): List<String> {
        return historyDataStore.deleteSearchedKeyword(targetKeyword)
    }

}