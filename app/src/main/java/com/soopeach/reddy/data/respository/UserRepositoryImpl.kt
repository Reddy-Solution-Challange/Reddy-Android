package com.soopeach.reddy.data.respository

import com.soopeach.reddy.data.datasource.UserDataSource
import com.soopeach.reddy.domain.respository.UserRepository

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
): UserRepository {

    override suspend fun signUp(googleToken: String): Boolean {
        return userDataSource.signUp(googleToken)
    }

    override suspend fun getAccessToken(): String {
        return userDataSource.getAccessToken()
    }

    override suspend fun getSearchedKeywords(): List<String> {
        return userDataSource.getSearchedKeywords()
    }

    override suspend fun updateSearchedKeyword(newKeyword: String): List<String> {
        return userDataSource.updateSearchedKeyword(newKeyword)
    }

    override suspend fun deleteSearchedKeyword(targetKeyword: String): List<String> {
        return userDataSource.deleteSearchedKeyword(targetKeyword)
    }
}