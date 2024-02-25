package com.soopeach.reddy.data.service

import com.soopeach.reddy.data.model.request.RequestToken
import com.soopeach.reddy.data.model.response.Response
import com.soopeach.reddy.data.model.response.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("api/v1/auth/google")
    suspend fun requestSignIn(@Body token: RequestToken): Response<TokenResponse>

}