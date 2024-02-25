package com.soopeach.reddy.data.model.response.paging.recyclingpost

data class  RecyclingPostPagingResponse<T>(
    val code: String,
    val data: RecyclingPostPagingData<T>,
    val message: String
)