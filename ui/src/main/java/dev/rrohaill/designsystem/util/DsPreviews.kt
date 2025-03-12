package dev.rrohaill.designsystem.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

const val LARGER_FONT_BREAKPOINT = 1.4f

@Preview(
    name = "1-portrait normal",
    group = "variants",
    locale = "sv",
    widthDp = 411,
    heightDp = 891,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "2-portrait small",
    group = "variants",
    widthDp = 411,
    heightDp = 700,
    locale = "en",
)
@Preview(
    name = "3-large fonts",
    group = "variants",
    widthDp = 411,
    heightDp = 891,
    locale = "en",
    fontScale = LARGER_FONT_BREAKPOINT,
)
@Preview(
    name = "4-landscape small",
    group = "variants",
    widthDp = 700,
    heightDp = 411,
    locale = "en",
    uiMode = UI_MODE_NIGHT_YES,
)
@Preview(
    widthDp = 1200,
    heightDp = 800,
    name = "5-landscape big",
    group = "variants",
    locale = "en",
)
@Preview(
    widthDp = 673,
    heightDp = 841,
    name = "6-squareish",
    group = "variants",
    locale = "sv",
)

annotation class DsPreviews
