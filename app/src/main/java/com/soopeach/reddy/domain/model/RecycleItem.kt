package com.soopeach.reddy.domain.model

import androidx.annotation.DrawableRes
import com.soopeach.reddy.R

enum class RecycleItem(
    val id: Int,
    val text: String,
    @DrawableRes val image: Int
) {
    PLASTIC(1, "Plastic", R.drawable.plastic),
    VINYL(2, "Vinyl", R.drawable.vinyl),
    PAPER(3, "Paper", R.drawable.paper),
    CAN(4, "Can", R.drawable.can);
}