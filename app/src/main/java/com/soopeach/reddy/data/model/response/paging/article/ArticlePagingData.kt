package com.soopeach.reddy.data.model.response.paging.article

import com.soopeach.reddy.data.model.response.paging.Page

data class ArticlePagingData<T>(
    val page: Page,
    val articles: T
)