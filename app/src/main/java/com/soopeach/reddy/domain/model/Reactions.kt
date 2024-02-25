package com.soopeach.reddy.domain.model

import androidx.annotation.DrawableRes
import com.soopeach.reddy.R

enum class Reactions(
    val text: String,
    val textForRequest: String,
    @DrawableRes val selectedImage: Int,
    @DrawableRes val unSelectedImage: Int
) {
    SOSO("SoSo", "SOSO", R.drawable.ch_soso_selected, R.drawable.ch_soso_unselected),
    BAD("Bad", "ANALYSIS", R.drawable.ch_bad_selected, R.drawable.ch_bad_unselected),
    WOW("Wow", "GOOD", R.drawable.ch_wow_selected, R.drawable.ch_wow_unselected),
    GOOD("Good", "EMPATHY", R.drawable.ch_good_selected, R.drawable.ch_good_unselected);

}