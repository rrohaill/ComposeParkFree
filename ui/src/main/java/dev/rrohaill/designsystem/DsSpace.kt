package dev.rrohaill.designsystem

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsDimensions

@SuppressLint("ComposableNaming")
object DsSpace {
    /**
     * 4.dp
     */
    @Stable
    @Composable
    fun xsmall() {
        Spacer(modifier = Modifier.size(dsDimensions.xSmallSpace))
    }

    /**
     * 8.dp
     */
    @Stable
    @Composable
    fun small() {
        Spacer(modifier = Modifier.size(dsDimensions.smallSpace))
    }

    /**
     * 12.dp
     */
    @Stable
    @Composable
    fun medium() {
        Spacer(modifier = Modifier.size(dsDimensions.mediumSpace))
    }

    /**
     * 16.dp
     */
    @Stable
    @Composable
    fun default() {
        Spacer(modifier = Modifier.size(dsDimensions.defaultSpace))
    }

    /**
     * 24.dp
     */
    @Stable
    @Composable
    fun large() {
        Spacer(modifier = Modifier.size(dsDimensions.largeSpace))
    }

    /**
     * 32.dp
     */
    @Stable
    @Composable
    fun xlarge() {
        Spacer(modifier = Modifier.size(dsDimensions.xLargeSpace))
    }
}