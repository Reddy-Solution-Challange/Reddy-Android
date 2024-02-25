package com.soopeach.reddy.domain.respository

interface UserRepository {

    suspend fun signUp(googleToken: String): Boolean

    suspend fun getAccessToken(): String

    suspend fun getSearchedKeywords(): List<String>

    suspend fun updateSearchedKeyword(newKeyword: String): List<String>

    suspend fun deleteSearchedKeyword(targetKeyword: String): List<String>

}