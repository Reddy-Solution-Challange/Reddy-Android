package com.soopeach.reddy.data.model.response.paging

data class Page(
    val currentPage: Int,
    val empty: Boolean,
    val numberOfElements: Int,
    val pageFirst: Boolean,
    val pageLast: Boolean,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int
)