package dev.rrohaill.designsystem.standard

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsColors
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsShapes

@SuppressLint("DsMaterialReplacementComposable")
@Composable
fun DsSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = dsColors.backgroundSecondary,
    contentColor: Color = contentColorFor(color),
    enabled: Boolean = true,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val (shadowElevation, tonalElevation) = when {
        isSystemInDarkTheme() -> {
            0.dp to elevation
        }

        dsColors.isDynamicTheme -> {
            0.dp to elevation
        }

        else -> {
            elevation to 0.dp
        }
    }
    if (onClick != null) {
        Surface(
            modifier = modifier,
            shape = shape,
            color = color,
            enabled = enabled,
            contentColor = contentColor,
            border = border,
            tonalElevation = tonalElevation,
            shadowElevation = shadowElevation,
            onClick = onClick,
            content = content
        )
    } else {
        Surface(
            modifier = modifier,
            shape = shape,
            color = color,
            contentColor = contentColor,
            border = border,
            tonalElevation = tonalElevation,
            shadowElevation = shadowElevation,
            content = content
        )
    }
}

@Composable
fun DsCard(
    modifier: Modifier = Modifier,
    shape: Shape = dsShapes.mediumRounding,
    surfaceColor: Color = dsColors.backgroundSecondary,
    contentColor: Color = contentColorFor(surfaceColor),
    border: BorderStroke? = null,
    elevation: Dp = 1.dp,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    DsSurface(
        modifier = modifier,
        shape = shape,
        color = surfaceColor,
        contentColor = contentColor,
        elevation = elevation,
        border = border,
        enabled = enabled,
        onClick = onClick,
        content = content
    )
}
