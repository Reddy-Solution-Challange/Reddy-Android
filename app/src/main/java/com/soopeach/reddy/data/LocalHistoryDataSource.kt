package com.soopeach.reddy.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.historyDataStore: DataStore<Preferences> by preferencesDataStore(name = "history")

class LocalHistoryDataStore(private val context: Context) {
    private val searchedKeywordKey = stringPreferencesKey("accessToken")

    suspend fun updateSearchedKeyword(newKeyword: String): List<String> {
        val previous = getSearchedKeywordToken()?.toConvertedList() ?: emptyList()
        val newHistories = (previous + newKeyword).takeLast(5).toConvertedString()
        updateSearchedKeywordToken(newHistories)
        return newHistories.toConvertedList()
    }

    suspend fun getSearchedKeyword() = getSearchedKeywordToken()?.toConvertedList() ?: emptyList()

    suspend fun deleteSearchedKeyword(targetKeyword: String): List<String> {
        val previous = getSearchedKeywordToken()?.toConvertedList() ?: emptyList()
        val newHistories = previous.filter { it != targetKeyword }.toConvertedString()
        updateSearchedKeywordToken(newHistories)
        return newHistories.toConvertedList()
    }

    private suspend fun getSearchedKeywordToken() = context.historyDataStore.data
        .map { preferences ->
            preferences[searchedKeywordKey]
        }.first()

    private suspend fun updateSearchedKeywordToken(accessToken: String) {
        context.historyDataStore.edit { preferences ->
            preferences[searchedKeywordKey] = accessToken
        }
    }

    private fun List<String>.toConvertedString(): String {
        return this.toString().replace("[", "").replace("]", "")
    }

    private fun String.toConvertedList(): List<String> {
        return this.split(",").map { it.trim() }
    }

}