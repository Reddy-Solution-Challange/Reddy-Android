package com.soopeach.reddy.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.soopeach.reddy.data.model.response.TokenResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.accountDataStore: DataStore<Preferences> by preferencesDataStore(name = "account")

class UserLocalDataStore(private val context: Context) {
    private val accessTokenKey = stringPreferencesKey("accessToken")
    private val refreshTokenKey = stringPreferencesKey("refreshToken")
    private val accessTokenExpiresInKey = longPreferencesKey("accessTokenExpiresIn")

    suspend fun updateToken(tokenResponse: TokenResponse) {
        updateAccessToken(tokenResponse.accessToken)
        updateRefreshToken(tokenResponse.refreshToken)
        updateAccessTokenExpiresIn(tokenResponse.accessTokenExpiresIn)
    }

    suspend fun getAccessToken() = context.accountDataStore.data
        .map { preferences ->
            preferences[accessTokenKey]
        }.first()

    private suspend fun updateAccessToken(accessToken: String) {
        context.accountDataStore.edit { preferences ->
            preferences[accessTokenKey] = accessToken
        }
    }

    suspend fun getRefreshToken() = context.accountDataStore.data
        .map { preferences ->
            preferences[refreshTokenKey]
        }.first()

    private suspend fun updateRefreshToken(refreshToken: String) {
        context.accountDataStore.edit { preferences ->
            preferences[refreshTokenKey] = refreshToken
        }
    }

    suspend fun getAccessExpiresInToken() = context.accountDataStore.data
        .map { preferences ->
            preferences[accessTokenExpiresInKey]
        }.first()

    private suspend fun updateAccessTokenExpiresIn(accessTokenExpiresIn: Long) {
        context.accountDataStore.edit { preferences ->
            preferences[accessTokenExpiresInKey] = accessTokenExpiresIn
        }
    }
}