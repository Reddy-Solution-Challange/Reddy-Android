package com.soopeach.reddy.data.model.response.paging.recyclingpost

import com.soopeach.reddy.data.model.response.paging.Page

data class RecyclingPostPagingData<T>(
    val page: Page,
    val separatePosts: T
)