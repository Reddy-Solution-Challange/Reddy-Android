package com.soopeach.reddy.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.soopeach.reddy.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val PretendardExtraBold = FontFamily(Font(R.font.pretendard_extrabold))
val PretendardBold = FontFamily(Font(R.font.pretendard_bold))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium))
val PretendardRegular = FontFamily(Font(R.font.pretendard_regular))

val Heading1 = TextStyle(
    fontFamily = PretendardExtraBold,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 32.sp,
    color = Black
)

val Heading2 = TextStyle(
    fontFamily = PretendardBold,
    fontWeight = FontWeight.Bold,
    fontSize = 28.sp,
    color = Black
)

val Heading3 = TextStyle(
    fontFamily = PretendardBold,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    color = Black
)

val Title1 = TextStyle(
    fontFamily = PretendardBold,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    color = Black
)

val Title2 = TextStyle(
    fontFamily = PretendardBold,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    color = Black
)

val Title3 = TextStyle(
    fontFamily = PretendardBold,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    color = Black
)

val Body1 = TextStyle(
    fontFamily = PretendardMedium,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    color = Black
)

val Body2 = TextStyle(
    fontFamily = PretendardMedium,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    color = Black
)

val Body3 = TextStyle(
    fontFamily = PretendardMedium,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    color = Black
)

val Body4 = TextStyle(
    fontFamily = PretendardMedium,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    color = Black
)

val Caption = TextStyle(
    fontFamily = PretendardRegular,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    color = Black
)