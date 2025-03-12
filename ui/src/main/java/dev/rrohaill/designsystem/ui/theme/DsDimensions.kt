package dev.rrohaill.designsystem.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class DsDimensions(
    // Spacing
    val xLargeSpace: Dp = 32.dp,
    val largeSpace: Dp = 24.dp,
    val defaultSpace: Dp = 16.dp,
    val mediumSpace: Dp = 12.dp,
    val smallSpace: Dp = 8.dp,
    val xSmallSpace: Dp = 4.dp,

    val listContentPaddingTight: PaddingValues = PaddingValues(
        horizontal = smallSpace,
        vertical = defaultSpace
    ),
    val listContentPaddingSpacious: PaddingValues = PaddingValues(
        horizontal = defaultSpace,
        vertical = defaultSpace
    ),
    val bottomSheetContentPadding: Dp = largeSpace
)

/**
 * This CompositionLocal holds on to the current definition of dimensions for this application as
 * described by the design system.
 *
 * To access values within this CompositionLocal, use [DsTheme.dsDimensions].
 */
internal val LocalDsDimensions = staticCompositionLocalOf { DsDimensions() }
