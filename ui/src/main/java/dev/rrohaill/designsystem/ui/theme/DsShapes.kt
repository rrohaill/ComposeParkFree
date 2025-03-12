package dev.rrohaill.designsystem.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class DsShapes(
    val noRounding: CornerBasedShape = RoundedCornerShape(0.dp),
    val round: CornerBasedShape = CircleShape,

    val smallRounding: CornerBasedShape = RoundedCornerShape(8.dp),
    val mediumRounding: CornerBasedShape = RoundedCornerShape(12.dp),

    val mediumTopRounding: CornerBasedShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
    val mediumBottomRounding: CornerBasedShape = RoundedCornerShape(
        bottomEnd = 12.dp,
        bottomStart = 12.dp
    ),

    val largeRounding: CornerBasedShape = RoundedCornerShape(16.dp),
    val largeTopRounding: CornerBasedShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    val largeBottomRounding: CornerBasedShape = RoundedCornerShape(
        bottomStart = 16.dp,
        bottomEnd = 16.dp
    ),
)

/**
 * This CompositionLocal holds on to the current definition of dimensions for this application as
 * described by the design system.
 *
 * To access values within this CompositionLocal, use [DsTheme.dsDimensions].
 */
internal val LocalDsShapes = staticCompositionLocalOf { DsShapes() }
