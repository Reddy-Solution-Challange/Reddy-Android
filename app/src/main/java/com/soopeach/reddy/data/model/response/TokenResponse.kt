package com.soopeach.reddy.data.model.response

data class TokenResponse(
    val accessToken: String,
    val accessTokenExpiresIn: Long,
    val bearerType: String,
    val refreshToken: String
)