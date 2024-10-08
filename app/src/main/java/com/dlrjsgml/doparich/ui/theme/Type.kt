package com.dlrjsgml.doparich.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dlrjsgml.doparich.R

val SuitFonfFamily = FontFamily(
    Font(R.font.suit_extrabold,FontWeight.ExtraBold),
    Font(R.font.suit_semibold,FontWeight.SemiBold),
    Font(R.font.suit_regular,FontWeight.Normal),
    Font(R.font.suit_bold,FontWeight.Bold)
)

val title1 = TextStyle(
    fontFamily = SuitFonfFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 24.sp
)

val title2 = TextStyle(
    fontFamily = SuitFonfFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 28.sp
)
val content0 = TextStyle(
    fontFamily = SuitFonfFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp
)
val content1 = TextStyle(
    fontFamily = SuitFonfFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 18.sp
)
val content2 = TextStyle(
    fontFamily = SuitFonfFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp
)
val contentbold = TextStyle(
    fontFamily = SuitFonfFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 16.sp
)



val caption1 = TextStyle(
    fontFamily = SuitFonfFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 8.sp
)
val caption2 = TextStyle(
    fontFamily = SuitFonfFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
val caption3 = TextStyle(
    fontFamily = SuitFonfFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)
val caption3Bold = TextStyle(
    fontFamily = SuitFonfFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 14.sp
)





// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)