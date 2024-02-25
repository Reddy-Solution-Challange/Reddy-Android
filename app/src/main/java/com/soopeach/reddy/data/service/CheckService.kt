package com.soopeach.reddy.data.service

import com.soopeach.reddy.data.model.request.RequestCheck
import com.soopeach.reddy.data.model.request.RequestSave
import com.soopeach.reddy.data.model.response.check.CheckedResult
import com.soopeach.reddy.data.model.response.Response
import com.soopeach.reddy.data.model.response.check.SavedResult
import com.soopeach.reddy.data.model.response.check.SavedResultDetail
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface CheckService {

    @POST("api/v1/check")
    suspend fun requestCheck(@Body imageUrl: RequestCheck): Response<CheckedResult>

    @GET("api/v1/check")
    suspend fun getSavedResults(
        @Header("Authorization") token: String
    ): Response<List<SavedResult>>

    @POST("api/v1/check/save")
    suspend fun saveResult(
        @Header("Authorization") token: String,
        @Body requestSave: RequestSave
    ): Response<String>

    @GET("api/v1/check/{groupId}")
    suspend fun getSavedResultDetail(
        @Header("Authorization") token: String,
        @Path("groupId") id: Int
    ): Response<SavedResultDetail>
}