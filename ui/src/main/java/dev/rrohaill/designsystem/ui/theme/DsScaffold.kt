package dev.rrohaill.designsystem.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsColors

@SuppressLint("DsMaterialReplacementComposable")
@Composable
fun DsScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    isBottomBarPresent: Boolean = false,
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    backgroundColor: Color = dsColors.backgroundPrimary,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable (BoxScope.() -> Unit)
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = {
            // BottomBars already adds paddings for navigationbars
            // So this makes sure we don't add it twice
            if (isBottomBarPresent) {
                floatingActionButton()
            } else {
                Box(Modifier.navigationBarsPadding()) {
                    floatingActionButton()
                    // If FAB is an empty compose block this is actually needed so snackbars appear correctly with
                    // navigation bar padding
                    Spacer(Modifier.size(1.dp))
                }
            }
        },
        contentWindowInsets = contentWindowInsets,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = backgroundColor,
        contentColor = contentColor,
    ) {
        Box(Modifier.padding(it)) {
            content()
        }
    }
}

@Composable
fun fullScreenWindowInset() =
    WindowInsets.systemBars.union(WindowInsets.navigationBars).only(WindowInsetsSides.Horizontal)