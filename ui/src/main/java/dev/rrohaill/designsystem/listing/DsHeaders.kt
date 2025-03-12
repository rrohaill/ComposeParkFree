package dev.rrohaill.designsystem.listing

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import dev.rrohaill.designsystem.standard.DsText
import dev.rrohaill.designsystem.text.UiText
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsColors
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsDimensions
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsTypography
import dev.rrohaill.designsystem.util.dsPlaceholder

// {"DesignComponent": "Small Section Header", "identifiers": ["KvSmallSectionHeader"]}
@Composable
fun DsSmallSectionHeader(
    modifier: Modifier = Modifier,
    label: UiText,
    isLoading: Boolean = false
) {
    DsText(
        modifier = modifier
            .padding(
                start = dsDimensions.smallSpace,
                top = dsDimensions.mediumSpace,
                bottom = dsDimensions.mediumSpace,
                end = dsDimensions.smallSpace
            )
            .dsPlaceholder(isLoading, dsColors.borderSubtle),
        text = label,
        style = dsTypography.BodyS,
        color = dsColors.textSecondary,
    )
}

// {"DesignComponent": "Medium Section Header", "identifiers": ["KvMediumSectionHeader"]}
@Composable
fun DsMediumSectionHeader(
    modifier: Modifier = Modifier,
    label: UiText,
    isLoading: Boolean = false
) {
    DsText(
        modifier = modifier
            .padding(
                start = dsDimensions.smallSpace,
                top = dsDimensions.mediumSpace,
                bottom = dsDimensions.mediumSpace,
                end = dsDimensions.smallSpace
            )
            .dsPlaceholder(isLoading),
        text = label,
        style = dsTypography.HeadingM,
        textAlign = TextAlign.Start,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}