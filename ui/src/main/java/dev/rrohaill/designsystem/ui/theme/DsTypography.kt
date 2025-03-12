package dev.rrohaill.designsystem.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/*
Here you can provide font
 */
@Suppress("unused")
val DesignSystemSans = null
val DesignSystemSansBold = null

// Changes the "g" for DMSans to be same as in Sans
private const val FONT_FEATURE_SETTING_STYLE = "ss03"

@Immutable
data class DsTypography(
    val DisplayL: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        letterSpacing = 0.sp,
        lineHeight = 52.sp,
        fontFamily = DesignSystemSansBold
    ),
    val DisplayM: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        letterSpacing = 0.sp,
        lineHeight = 42.sp,
        fontFamily = DesignSystemSansBold
    ),
    val HeadingL: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 0.sp,
        lineHeight = 31.sp,
        fontFamily = DesignSystemSansBold
    ),
    val HeadingM: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.sp,
        lineHeight = 26.sp,
        fontFamily = DesignSystemSansBold
    ),
    val HeadingS: TextStyle = TextStyle(
        fontFeatureSettings = FONT_FEATURE_SETTING_STYLE,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
    ),
    val TitleS: TextStyle = TextStyle(
        fontFeatureSettings = FONT_FEATURE_SETTING_STYLE,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
    ),
    val HeadingXS: TextStyle = TextStyle(
        fontFeatureSettings = FONT_FEATURE_SETTING_STYLE,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
    ),
    val ButtonM: TextStyle = TextStyle(
        fontFeatureSettings = FONT_FEATURE_SETTING_STYLE,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        letterSpacing = (-0.2).sp,
        lineHeight = 21.sp,
    ),
    val ButtonS: TextStyle = TextStyle(
        fontFeatureSettings = FONT_FEATURE_SETTING_STYLE,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        letterSpacing = 0.sp,
        lineHeight = 18.sp,
    ),
    val TitleM: TextStyle = TextStyle(
        fontFeatureSettings = FONT_FEATURE_SETTING_STYLE,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
    ),

    val BodyM: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
    ),
    val BodyS: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
    ),
    val Overline: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
    ),
    val Caption: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
    ),
    val Monospace: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        fontFamily = FontFamily.Monospace
    ),
)

/**
 * This CompositionLocal holds on to the current definition of typography for this application as
 * described by the Material spec. You can read the values in it when creating custom components
 * that want to use Material types, as well as override the values when you want to re-style a
 * part of your hierarchy. Material components related to text such as [Button] will use this
 * CompositionLocal to set values with which to style children text components.
 *
 * To access values within this CompositionLocal, use [MaterialTheme.typography].
 */
internal val LocalDsTypography = staticCompositionLocalOf { DsTypography() }
