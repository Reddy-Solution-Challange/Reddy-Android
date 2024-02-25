package com.soopeach.reddy.data.model.response

data class EmotionResult(
    val isAnalysisEmotion: Boolean,
    val isEmpathyEmotion: Boolean,
    val isGoodEmotion: Boolean,
    val isSosoEmotion: Boolean
)