package dev.rrohaill.designsystem.input

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.rrohaill.designsystem.DsSpace
import dev.rrohaill.designsystem.DsTestTags
import dev.rrohaill.designsystem.models.UiImage
import dev.rrohaill.designsystem.standard.DsCard
import dev.rrohaill.designsystem.standard.DsIcon
import dev.rrohaill.designsystem.standard.DsSurface
import dev.rrohaill.designsystem.standard.DsText
import dev.rrohaill.designsystem.standard.IconSize
import dev.rrohaill.designsystem.text.UiText
import dev.rrohaill.designsystem.text.uiText
import dev.rrohaill.designsystem.ui.theme.DesignSystemTheme
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsColors
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsShapes
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsTypography
import dev.rrohaill.parkfree.R

object DsButtonDefaults {
    val MinWidthFAB = 240.dp
    val MinWidth = ButtonDefaults.MinWidth
    val ButtonPaddingMedium = PaddingValues(
        vertical = 8.dp,
        horizontal = 24.dp,
    )
}

sealed class DsButtonStyle(
    val minHeight: Dp,
    val font: @Composable () -> TextStyle,
    val buttonPaddings: PaddingValues,
) {
    data object M :
        DsButtonStyle(52.dp, { dsTypography.ButtonM }, DsButtonDefaults.ButtonPaddingMedium)

    data object S : DsButtonStyle(
        44.dp,
        { dsTypography.ButtonS },
        PaddingValues(vertical = 8.dp, horizontal = 16.dp)
    )

    data object XS : DsButtonStyle(
        36.dp,
        { dsTypography.ButtonS },
        PaddingValues(vertical = 7.dp, horizontal = 12.dp)
    )

    data object LinkM : DsButtonStyle(52.dp, { dsTypography.ButtonM }, PaddingValues(0.dp, 8.dp))
    data object LinkS : DsButtonStyle(44.dp, { dsTypography.ButtonS }, PaddingValues(0.dp, 8.dp))
}

// {"DesignComponent": "Primary button", "identifiers": ["DsPrimaryButton"]}
@Composable
fun DsPrimaryButton(
    modifier: Modifier = Modifier,
    label: @Composable RowScope.() -> Unit,
    leadingIcon: (@Composable RowScope.() -> Unit)? = null,
    trailingIcon: (@Composable RowScope.() -> Unit)? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    contentPadding: PaddingValues = DsButtonDefaults.ButtonPaddingMedium,
) {
    InternalPrimaryButton(
        modifier = modifier,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onClick = onClick,
        enabled = enabled,
        isLoading = isLoading,
        contentPadding = contentPadding,
    )
}

// {"DesignComponent": "Small floating action button", "identifiers": ["DsSmallFloatingActionButton"]}
@Composable
fun DsSmallFloatingActionButton(
    modifier: Modifier = Modifier,
    icon: UiImage,
    label: UiText,
    listState: LazyListState,
    isVisible: Boolean,
    onClick: () -> Unit,
) {
    val isExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0 } }
    DsSmallFloatingActionButton(
        modifier = modifier,
        icon = icon,
        label = label,
        isExpanded = isExpanded,
        isVisible = isVisible,
        onClick = onClick,
    )
}

@Composable
fun DsSmallFloatingActionButton(
    modifier: Modifier = Modifier,
    icon: UiImage,
    label: UiText,
    gridState: LazyGridState,
    isVisible: Boolean,
    onClick: () -> Unit,
) {
    val isExpanded by remember { derivedStateOf { gridState.firstVisibleItemIndex == 0 && gridState.isScrollInProgress.not() && gridState.firstVisibleItemScrollOffset == 0 } }
    DsSmallFloatingActionButton(
        modifier = modifier,
        icon = icon,
        label = label,
        isExpanded = isExpanded,
        isVisible = isVisible,
        onClick = onClick,
    )
}

@Composable
fun DsSmallFloatingActionButton(
    modifier: Modifier = Modifier,
    icon: UiImage,
    label: UiText,
    isExpanded: Boolean,
    isVisible: Boolean,
    onClick: () -> Unit,
) {
    val animationSpec = tween<Float>(
        durationMillis = 300,
        delayMillis = 400,
        easing = LinearOutSlowInEasing
    )
    val context = LocalContext.current
    ProvideTextStyle(value = DsButtonStyle.M.font.invoke()) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = animationSpec),
            exit = fadeOut()
        ) {
            FloatingActionButton(
                modifier = modifier
                    .testTag(DsTestTags.DsFab)
                    .semantics {
                        if (isExpanded.not()) {
                            contentDescription = label.getString(context)
                        }
                    },
                onClick = onClick,
                containerColor = dsColors.buttonPrimary,
                content = {
                    Row(
                        modifier = Modifier.padding(
                            start = 12.dp,
                            end = 12.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        DsIcon(icon)
                        val enterSpec =
                            fadeIn(animationSpec = animationSpec) + expandIn(expandFrom = Alignment.CenterEnd)
                        val exitSpec = fadeOut() + shrinkOut(shrinkTowards = Alignment.CenterEnd)
                        AnimatedVisibility(
                            visible = isExpanded,
                            enter = enterSpec,
                            exit = exitSpec
                        ) {
                            Row {
                                DsSpace.small()
                                DsText(label)
                            }
                        }
                    }
                },
            )
        }
    }
}

@Composable
fun DsFloatingActionButton(
    modifier: Modifier = Modifier,
    label: @Composable RowScope.() -> Unit,
    leadingIcon: (@Composable RowScope.() -> Unit)? = null,
    trailingIcon: (@Composable RowScope.() -> Unit)? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    contentPadding: PaddingValues = DsButtonDefaults.ButtonPaddingMedium,
) {
    InternalPrimaryButton(
        modifier = modifier
            .testTag(DsTestTags.DsFab)
            .widthIn(min = DsButtonDefaults.MinWidthFAB),
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onClick = onClick,
        enabled = enabled,
        isLoading = isLoading,
        contentPadding = contentPadding,
    )
}

@SuppressLint("DsMaterialReplacementComposable")
@Composable
internal fun InternalPrimaryButton(
    modifier: Modifier = Modifier,
    label: @Composable RowScope.() -> Unit,
    leadingIcon: (@Composable RowScope.() -> Unit)? = null,
    trailingIcon: (@Composable RowScope.() -> Unit)? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = DsButtonDefaults.ButtonPaddingMedium,
) {
    val isEnabled = enabled && isLoading.not()
    val interactionSource = remember { MutableInteractionSource() }
    Button(
        onClick = onClick,
        modifier = Modifier
            .widthIn(max = 400.dp)
            .defaultMinSize(
                minWidth = DsButtonDefaults.MinWidth,
                minHeight = DsButtonStyle.M.minHeight,
            )
            .then(modifier),
        enabled = isEnabled,
        colors = colors,
        interactionSource = interactionSource,
        elevation = ButtonDefaults.buttonElevation(),
        shape = dsShapes.mediumRounding,
        border = null,
        contentPadding = contentPadding
    ) {
        Box(contentAlignment = Alignment.Center) {
            val loadingProgressAnimation by animateFloatAsState(
                targetValue = if (isLoading) 1f else 0f,
                label = "crossfade content",
            )
            Row(
                modifier = Modifier.alpha(1f - loadingProgressAnimation),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                    DsSpace.small()
                }
                ProvideTextStyle(value = DsButtonStyle.M.font.invoke()) {
                    label()
                }
                if (trailingIcon != null) {
                    DsSpace.small()
                    trailingIcon()
                }
            }
            CircularProgressIndicator(Modifier.alpha(loadingProgressAnimation))
        }
    }
}

// {"DesignComponent": "Dismissive Button", "identifiers": ["DsDismissiveButton"]}
@Composable
fun DsDismissiveButton(
    modifier: Modifier = Modifier,
    label: @Composable RowScope.() -> Unit,
    leadingIcon: (@Composable RowScope.() -> Unit)? = null,
    trailingIcon: (@Composable RowScope.() -> Unit)? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    contentPadding: PaddingValues = DsButtonDefaults.ButtonPaddingMedium,
) {
    InternalPrimaryButton(
        modifier = modifier,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onClick = onClick,
        enabled = enabled,
        isLoading = isLoading,
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = dsColors.buttonDismissive,
            contentColor = dsColors.onSurfaceError,
        ),
    )
}

// {"DesignComponent": "Secondary Button", "identifiers": ["DsSecondaryButton"]}
@SuppressLint("DsMaterialReplacementComposable")
@Composable
fun DsSecondaryButton(
    modifier: Modifier = Modifier,
    buttonStyle: DsButtonStyle = DsButtonStyle.M,
    label: @Composable () -> Unit,
    icon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    color: Color = dsColors.textPrimary,
    borderColor: Color = dsColors.buttonBorder,
    borderWidth: Dp = 1.dp,
    isLoading: Boolean = false,
) {
    OutlinedButton(
        onClick = onClick,
        Modifier
            .widthIn(max = 400.dp)
            .defaultMinSize(
                minWidth = DsButtonDefaults.MinWidth,
                minHeight = buttonStyle.minHeight,
            )
            .then(modifier),
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = null,
        shape = dsShapes.mediumRounding,
        border = BorderStroke(borderWidth, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = color
        ),
        contentPadding = buttonStyle.buttonPaddings,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (icon != null) {
                    icon()
                    DsSpace.small()
                }
                ProvideTextStyle(value = buttonStyle.font.invoke()) {
                    CompositionLocalProvider(
                        LocalContentColor provides color,
                        content = label
                    )
                }
            }
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

// {"DesignComponent": "Text Button", "identifiers": ["DsTextButton"]}
@Composable
fun DsTextButton(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable RowScope.() -> Unit)? = null,
    trailingIcon: (@Composable RowScope.() -> Unit)? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    contentColor: Color = dsColors.textPrimary,
    contentPadding: PaddingValues = DsButtonDefaults.ButtonPaddingMedium,
    text: UiText,
) {
    DsTextButton(
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        onClick = onClick,
        enabled = enabled,
        contentColor = contentColor,
        contentPadding = contentPadding,
    ) {
        DsText(text = text)
    }
}

@SuppressLint("DsMaterialReplacementComposable")
@Composable
fun DsTextButton(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable RowScope.() -> Unit)? = null,
    trailingIcon: (@Composable RowScope.() -> Unit)? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    contentColor: Color = dsColors.textPrimary,
    contentPadding: PaddingValues = DsButtonDefaults.ButtonPaddingMedium,
    label: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    OutlinedButton(
        onClick = onClick,
        Modifier
            .widthIn(max = 400.dp)
            .defaultMinSize(
                minWidth = DsButtonDefaults.MinWidth,
                minHeight = DsButtonStyle.M.minHeight,
            )
            .then(modifier),
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = null,
        shape = dsShapes.mediumRounding,
        border = null,
        colors = ButtonDefaults.textButtonColors(
            contentColor = contentColor
        ),
        contentPadding = contentPadding
    ) {
        if (leadingIcon != null) {
            leadingIcon()
            DsSpace.small()
        }
        ProvideTextStyle(value = DsButtonStyle.M.font.invoke()) {
            CompositionLocalProvider(
                LocalContentColor provides contentColor,
                content = label
            )
        }
        if (trailingIcon != null) {
            DsSpace.small()
            trailingIcon()
        }
    }
}

// {"DesignComponent": "Link Button", "identifiers": ["DsLinkButton"]}
@SuppressLint("DsMaterialReplacementComposable")
@Composable
fun DsLinkButton(
    modifier: Modifier = Modifier,
    buttonStyle: DsButtonStyle = DsButtonStyle.LinkM,
    text: UiText,
    enabled: Boolean = true,
    leadingIcon: (@Composable RowScope.() -> Unit)? = {
        DsIcon(UiImage.of(R.drawable.ic_open_externally))
    },
    trailingIcon: (@Composable RowScope.() -> Unit)? = null,
    contentPadding: PaddingValues = buttonStyle.buttonPaddings,
    contentColor: Color = dsColors.linkPrimary,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    TextButton(
        onClick = onClick,
        Modifier
            .widthIn(max = 400.dp)
            .defaultMinSize(
                minWidth = DsButtonDefaults.MinWidth,
                minHeight = buttonStyle.minHeight,
            )
            .then(modifier),
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = null,
        shape = dsShapes.mediumRounding,
        border = null,
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Transparent,
            contentColor = contentColor
        ),
        contentPadding = contentPadding
    ) {
        if (leadingIcon != null) {
            leadingIcon()
            DsSpace.small()
        }
        ProvideTextStyle(value = buttonStyle.font.invoke()) {
            DsText(
                text = text,
            )
        }
        if (trailingIcon != null) {
            DsSpace.small()
            trailingIcon()
        }
    }
}

// {"DesignComponent": "Button XS", "identifiers": ["DsButtonXS"]}
@Deprecated(
    "Should not be used. Use DsSecondaryButton with XS size",
    replaceWith = ReplaceWith("DsSecondaryButton")
)
@Composable
fun DsButtonXS(
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit,
    icon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    DsSurface(
        onClick = onClick,
        color = dsColors.surfaceNeutral,
        shape = CircleShape,
        modifier = modifier.requiredHeightIn(min = 36.dp),
    ) {
        // Inside a Row pack the Image and text together to
        // show inside the chip
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(12.dp),
        ) {
            CompositionLocalProvider(
                LocalContentColor provides dsColors.textPrimary,
            ) {
                if (icon != null) {
                    icon()
                    DsSpace.xsmall()
                }
                ProvideTextStyle(value = dsTypography.BodyS) {
                    label()
                }
            }
        }
    }
}

// {"DesignComponent": "Icon Button", "identifiers": ["DsIconButton"]}
@SuppressLint("DsMaterialReplacementComposable")
@Composable
fun DsIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource,
        content = content
    )
}

@Preview(locale = "en", showBackground = true)
@Preview(locale = "en", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewButtons() {
    DesignSystemTheme {
        DsCard {
            PreviewAllButtons()
        }
    }
}

@Composable
fun PreviewAllButtons() {
    Column(
        Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DsPrimaryButton(
            Modifier.fillMaxWidth(),
            leadingIcon = {
                DsIcon(UiImage.of(R.drawable.ic_person))
            },
            label = { DsText("DsPrimaryButtonM".uiText()) },
            onClick = {}
        )
        DsDismissiveButton(
            Modifier.fillMaxWidth(),
            label = { DsText("DsDismissiveButton".uiText()) },
            onClick = {}
        )
        DsPrimaryButton(
            leadingIcon = {
                DsIcon(UiImage.of(R.drawable.ic_person))
            },
            label = { DsText("DsPrimaryButtonM loading".uiText()) },
            isLoading = true,
            onClick = {}
        )

        DsSecondaryButton(
            icon = {
                DsIcon(UiImage.of(id = R.drawable.ic_person))
            },
            label = {
                DsText("DsSecondaryButtonM".uiText())
            },
            onClick = {}
        )

        DsTextButton(
            leadingIcon = {
                DsIcon(
                    icon = UiImage.of(R.drawable.ic_person),
                )
            },
            label = {
                DsText(text = "DsTextButtonM".uiText())
            },
            onClick = { }
        )

        DsLinkButton(
            text = "DsLinkButtonM".uiText(),
            leadingIcon = {
                DsIcon(UiImage.of(R.drawable.ic_open_externally))
            },
            onClick = {}
        )

        DsSecondaryButton(
            buttonStyle = DsButtonStyle.S,
            icon = {
                DsIcon(UiImage.of(id = R.drawable.ic_person))
            },
            label = {
                DsText("DsSecondaryButtonS".uiText())
            },
            onClick = {}
        )

        DsLinkButton(
            text = "DsLinkButtonS".uiText(),
            buttonStyle = DsButtonStyle.LinkS,
            leadingIcon = {
                DsIcon(UiImage.of(R.drawable.ic_open_externally))
            },
            onClick = { }
        )

        @Suppress("DEPRECATION")
        DsButtonXS(
            label = { DsText("DsButtonXS".uiText()) },
            icon = {
                DsIcon(
                    UiImage.of(R.drawable.ic_person),
                    iconSize = IconSize.Small,
                )
            }
        )
    }
}
