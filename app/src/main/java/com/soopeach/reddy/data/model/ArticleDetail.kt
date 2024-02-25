package com.soopeach.reddy.data.model

data class ArticleDetail(
    val articleImageUrl: String?,
    val contents: String,
    val date: String,
    val isAnalysisEmotion: Boolean,
    val isEmpathyEmotion: Boolean,
    val isGoodEmotion: Boolean,
    val isSosoEmotion: Boolean,
    val link: String?,
    val title: String
)