package com.soopeach.reddy.data.model.response

data class Response<T>(
    val code: String,
    val data: T,
    val message: String
)