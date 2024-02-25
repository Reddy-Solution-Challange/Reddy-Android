package com.soopeach.reddy.data.model.response.paging.article

data class  ArticlePagingResponse<T>(
    val code: String,
    val data: ArticlePagingData<T>,
    val message: String
)