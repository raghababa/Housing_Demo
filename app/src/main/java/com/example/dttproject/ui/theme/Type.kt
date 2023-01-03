package com.example.dttproject.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dttproject.R

private val Gotham = FontFamily(
    Font(R.font.gothamssm_bold, FontWeight.Bold),
    Font(R.font.gothamssm_medium, FontWeight.W500),
    Font(R.font.gothamssm_light),
    Font(R.font.gothamssm_book)
)
private val GothamBook = FontFamily(
    Font(R.font.gothamssm_book)
)
private val GothamBold = FontFamily(
    Font(R.font.gothamssm_bold, FontWeight.Bold),
)
private val GothamMedium = FontFamily(
    Font(R.font.gothamssm_medium, FontWeight.W500),

)
private val GothamLight = FontFamily(
    Font(R.font.gothamssm_light),
)

// Set of Material typography styles to start with
val typography = Typography(
    h2 = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.W600,
        fontSize = 48.sp
    ),
    h3 = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    h4 = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp
    ),
    h6 = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,

    ),
    subtitle2 = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp,

    ),
    body2 = TextStyle(
        fontFamily = Gotham,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    button = TextStyle(
        fontFamily = Gotham,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = GothamBook,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = GothamBook,
        fontSize = 8.sp

    )


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)