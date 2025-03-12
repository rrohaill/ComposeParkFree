package dev.rrohaill.designsystem.ui.theme;

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class DsElevations(
    // Elevation
    val flatElevation: Dp = 0.dp,
    val smallElevation: Dp = 2.dp,
    val mediumElevation: Dp = 4.dp,
    val largeElevation: Dp = 8.dp,

    // Material elevations
    val cardElevation: Dp = 1.dp,
    val topAppBarElevation: Dp = 4.dp,
    val bottomAppBarElevation: Dp = 8.dp
)

/**
 * This CompositionLocal holds on to the current definition of dimensions for this application as
 * described by the design system.
 *
 * To access values within this CompositionLocal, use [DsTheme.dsDimensions].
 */
internal val LocalDsElevations = staticCompositionLocalOf { DsElevations() }
