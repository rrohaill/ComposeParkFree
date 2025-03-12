package dev.rrohaill.designsystem.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Stable
class DsColors(
    textPrimary: Color,
    textSecondary: Color,
    textBlack: Color,
    textWhite: Color,
    textInverted: Color,
    backgroundPrimary: Color,
    backgroundSecondary: Color,
    backgroundTertiary: Color,
    backgroundInverted: Color,
    backgroundWhite: Color,
    surfaceNeutral: Color,
    borderDistinct: Color,
    borderSubtle: Color,
    borderInput: Color,
    buttonPrimary: Color,
    buttonBorder: Color,
    buttonDismissive: Color,
    linkPrimary: Color,
    activeHighlight: Color,
    bottomNavigationHighlight: Color,
    notification: Color,
    surfaceSuccess: Color,
    onSurfaceSuccess: Color,
    surfaceHighlight: Color,
    onSurfaceHighlight: Color,
    surfaceInfo: Color,
    onSurfaceInfo: Color,
    surfaceError: Color,
    onSurfaceError: Color,
    surfaceErrorLight: Color,
    onSurfaceErrorLight: Color,
    errorText: Color,
    disabledSurface: Color,
    onDisabledSurface: Color,
    disabledBorder: Color,
    surfaceTint: Color,
    sharedSurface: Color,
    onSharedSurface: Color,
    bankIdBlue: Color,

    /**
     * These colors below should be seldom used.
     */
    green100: Color,
    green150: Color,
    green300: Color,
    green400: Color,
    green500: Color,

    amber100: Color,
    amber200: Color,
    amber300: Color,
    amber400: Color,
    amber500: Color,

    indigo100: Color,
    indigo200: Color,
    indigo300: Color,
    indigo400: Color,
    indigo500: Color,

    splashBackground: Color,

    isDynamicTheme: Boolean = false,
) {
    var textPrimary by mutableStateOf(textPrimary)
        internal set
    var textSecondary by mutableStateOf(textSecondary)
        internal set
    var textBlack by mutableStateOf(textBlack)
        internal set
    var textWhite by mutableStateOf(textWhite)
        internal set
    var textInverted by mutableStateOf(textInverted)
        internal set
    var backgroundPrimary by mutableStateOf(backgroundPrimary)
        internal set
    var backgroundSecondary by mutableStateOf(backgroundSecondary)
        internal set
    var backgroundTertiary by mutableStateOf(backgroundTertiary)
        internal set
    var backgroundInverted by mutableStateOf(backgroundInverted)
        internal set
    var backgroundWhite by mutableStateOf(backgroundWhite)
        internal set
    var surfaceNeutral by mutableStateOf(surfaceNeutral)
        internal set
    var borderDistinct by mutableStateOf(borderDistinct)
        internal set
    var borderSubtle by mutableStateOf(borderSubtle)
        internal set
    var borderInput by mutableStateOf(borderInput)
        internal set
    var buttonPrimary by mutableStateOf(buttonPrimary)
        internal set
    var buttonBorder by mutableStateOf(buttonBorder)
        internal set
    var buttonDismissive by mutableStateOf(buttonDismissive)
        internal set
    var linkPrimary by mutableStateOf(linkPrimary)
        internal set
    var activeHighlight by mutableStateOf(activeHighlight)
        internal set
    var bottomNavigationHighlight by mutableStateOf(bottomNavigationHighlight)
        internal set
    var splashBackground by mutableStateOf(splashBackground)
        internal set
    var notification by mutableStateOf(notification)
        internal set
    var surfaceSuccess by mutableStateOf(surfaceSuccess)
        internal set
    var onSurfaceSuccess by mutableStateOf(onSurfaceSuccess)
        internal set
    var surfaceHighlight by mutableStateOf(surfaceHighlight)
        internal set
    var onSurfaceHighlight by mutableStateOf(onSurfaceHighlight)
        internal set
    var surfaceInfo by mutableStateOf(surfaceInfo)
        internal set
    var onSurfaceInfo by mutableStateOf(onSurfaceInfo)
        internal set
    var surfaceError by mutableStateOf(surfaceError)
        internal set
    var onSurfaceError by mutableStateOf(onSurfaceError)
        internal set
    var surfaceErrorLight by mutableStateOf(surfaceErrorLight)
        internal set
    var onSurfaceErrorLight by mutableStateOf(onSurfaceErrorLight)
        internal set
    var errorText by mutableStateOf(errorText)
        internal set
    var disabledSurface by mutableStateOf(disabledSurface)
        internal set
    var onDisabledSurface by mutableStateOf(onDisabledSurface)
        internal set
    var disabledBorder by mutableStateOf(disabledBorder)
        internal set
    var surfaceTint by mutableStateOf(surfaceTint)
        internal set
    var sharedSurface by mutableStateOf(sharedSurface)
        internal set
    var onSharedSurface by mutableStateOf(onSharedSurface)
        internal set
    var bankIdBlue by mutableStateOf(bankIdBlue)
        internal set
    var green100 by mutableStateOf(green100)
        internal set
    var green200 by mutableStateOf(green150)
        internal set
    var green300 by mutableStateOf(green300)
        internal set
    var green400 by mutableStateOf(green400)
        internal set
    var green500 by mutableStateOf(green500)
        internal set
    var amber100 by mutableStateOf(amber100)
        internal set
    var amber200 by mutableStateOf(amber200)
        internal set
    var amber300 by mutableStateOf(amber300)
        internal set
    var amber400 by mutableStateOf(amber400)
        internal set
    var amber500 by mutableStateOf(amber500)
        internal set
    var indigo100 by mutableStateOf(indigo100)
        internal set
    var indigo200 by mutableStateOf(indigo200)
        internal set
    var indigo300 by mutableStateOf(indigo300)
        internal set
    var indigo400 by mutableStateOf(indigo400)
        internal set
    var indigo500 by mutableStateOf(indigo500)
        internal set
    var isDynamicTheme by mutableStateOf(isDynamicTheme)
        internal set

    fun update(other: DsColors) {
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textBlack = other.textBlack
        textWhite = other.textWhite
        textInverted = other.textInverted
        backgroundPrimary = other.backgroundPrimary
        backgroundSecondary = other.backgroundSecondary
        backgroundTertiary = other.backgroundTertiary
        backgroundInverted = other.backgroundInverted
        backgroundWhite = other.backgroundWhite
        surfaceNeutral = other.surfaceNeutral
        borderDistinct = other.borderDistinct
        borderSubtle = other.borderSubtle
        borderInput = other.borderInput
        buttonPrimary = other.buttonPrimary
        buttonBorder = other.buttonBorder
        buttonDismissive = other.buttonDismissive
        linkPrimary = other.linkPrimary
        activeHighlight = other.activeHighlight
        notification = other.notification
        surfaceSuccess = other.surfaceSuccess
        onSurfaceSuccess = other.onSurfaceSuccess
        surfaceHighlight = other.surfaceHighlight
        onSurfaceHighlight = other.onSurfaceHighlight
        surfaceInfo = other.surfaceInfo
        onSurfaceInfo = other.onSurfaceInfo
        surfaceError = other.surfaceError
        onSurfaceError = other.onSurfaceError
        errorText = other.errorText
        disabledSurface = other.disabledSurface
        onDisabledSurface = other.onDisabledSurface
        bankIdBlue = other.bankIdBlue
        disabledBorder = other.disabledBorder
        green100 = other.green100
        green200 = other.green200
        green300 = other.green300
        green400 = other.green400
        green500 = other.green500
        amber100 = other.amber100
        amber200 = other.amber200
        amber300 = other.amber300
        amber400 = other.amber400
        amber500 = other.amber500
        indigo100 = other.indigo100
        indigo200 = other.indigo200
        indigo300 = other.indigo300
        indigo400 = other.indigo400
        indigo500 = other.indigo500
        bottomNavigationHighlight = other.bottomNavigationHighlight
        surfaceTint = other.surfaceTint
        sharedSurface = other.sharedSurface
        onSharedSurface = other.onSharedSurface
        isDynamicTheme = other.isDynamicTheme
    }

    fun copy(
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        textBlack: Color = this.textBlack,
        textWhite: Color = this.textWhite,
        textInverted: Color = this.textInverted,
        backgroundPrimary: Color = this.backgroundPrimary,
        backgroundSecondary: Color = this.backgroundSecondary,
        backgroundTertiary: Color = this.backgroundTertiary,
        backgroundInverted: Color = this.backgroundInverted,
        backgroundWhite: Color = this.backgroundWhite,
        surfaceNeutral: Color = this.surfaceNeutral,
        borderDistinct: Color = this.borderDistinct,
        borderSubtle: Color = this.borderSubtle,
        borderInput: Color = this.borderInput,
        buttonPrimary: Color = this.buttonPrimary,
        buttonBorder: Color = this.buttonBorder,
        buttonDismissive: Color = this.buttonDismissive,
        linkPrimary: Color = this.linkPrimary,
        activeHighlight: Color = this.activeHighlight,
        notification: Color = this.notification,
        surfaceSuccess: Color = this.surfaceSuccess,
        onSurfaceSuccess: Color = this.onSurfaceSuccess,
        surfaceInformationHighlight: Color = this.surfaceHighlight,
        onSurfaceInformationHighlight: Color = this.onSurfaceHighlight,
        surfaceInformationTemporary: Color = this.surfaceInfo,
        onSurfaceInformationTemporary: Color = this.onSurfaceInfo,
        surfaceError: Color = this.surfaceError,
        onSurfaceError: Color = this.onSurfaceError,
        surfaceErrorLight: Color = this.surfaceErrorLight,
        onSurfaceErrorLight: Color = this.onSurfaceErrorLight,
        errorText: Color = this.errorText,
        disabledSurface: Color = this.disabledSurface,
        onDisabledSurface: Color = this.onDisabledSurface,
        disabledBorder: Color = this.disabledBorder,
        surfaceTint: Color = this.surfaceTint,
        sharedSurface: Color = this.sharedSurface,
        onSharedSurface: Color = this.onSharedSurface,
        bankIdBlue: Color = this.bankIdBlue,
        green100: Color = this.green100,
        green200: Color = this.green200,
        green300: Color = this.green300,
        green400: Color = this.green400,
        green500: Color = this.green500,
        amber100: Color = this.amber100,
        amber200: Color = this.amber200,
        amber300: Color = this.amber300,
        amber400: Color = this.amber400,
        amber500: Color = this.amber500,
        indigo100: Color = this.indigo100,
        indigo200: Color = this.indigo200,
        indigo300: Color = this.indigo300,
        indigo400: Color = this.indigo400,
        indigo500: Color = this.indigo500,
        bottomNavigationHighlight: Color = this.bottomNavigationHighlight,
        splashBackground: Color = this.splashBackground,
        isDynamicTheme: Boolean = this.isDynamicTheme,
    ): DsColors {
        return DsColors(
            textPrimary = textPrimary,
            textSecondary = textSecondary,
            textBlack = textBlack,
            textWhite = textWhite,
            textInverted = textInverted,
            backgroundPrimary = backgroundPrimary,
            backgroundSecondary = backgroundSecondary,
            backgroundTertiary = backgroundTertiary,
            backgroundInverted = backgroundInverted,
            backgroundWhite = backgroundWhite,
            surfaceNeutral = surfaceNeutral,
            borderDistinct = borderDistinct,
            borderSubtle = borderSubtle,
            borderInput = borderInput,
            buttonPrimary = buttonPrimary,
            buttonBorder = buttonBorder,
            buttonDismissive = buttonDismissive,
            linkPrimary = linkPrimary,
            activeHighlight = activeHighlight,
            bottomNavigationHighlight = bottomNavigationHighlight,
            notification = notification,
            surfaceSuccess = surfaceSuccess,
            onSurfaceSuccess = onSurfaceSuccess,
            surfaceHighlight = surfaceInformationHighlight,
            onSurfaceHighlight = onSurfaceInformationHighlight,
            surfaceInfo = surfaceInformationTemporary,
            onSurfaceInfo = onSurfaceInformationTemporary,
            surfaceError = surfaceError,
            onSurfaceError = onSurfaceError,
            surfaceErrorLight = surfaceErrorLight,
            onSurfaceErrorLight = onSurfaceErrorLight,
            errorText = errorText,
            disabledSurface = disabledSurface,
            onDisabledSurface = onDisabledSurface,
            disabledBorder = disabledBorder,
            surfaceTint = surfaceTint,
            sharedSurface = sharedSurface,
            onSharedSurface = onSharedSurface,
            bankIdBlue = bankIdBlue,
            green100 = green100,
            green150 = green200,
            green300 = green300,
            green400 = green400,
            green500 = green500,
            amber100 = amber100,
            amber200 = amber200,
            amber300 = amber300,
            amber400 = amber400,
            amber500 = amber500,
            indigo100 = indigo100,
            indigo200 = indigo200,
            indigo300 = indigo300,
            indigo400 = indigo400,
            indigo500 = indigo500,
            splashBackground = splashBackground,
            isDynamicTheme = isDynamicTheme,
        )
    }

}

/**
 * Material 3 variant matching MaterialTheme.colorScheme.surface
 */
@Composable
fun surfaceColorAtElevation(elevation: Dp): Color {
    return MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
}

internal val LocalDsColors = staticCompositionLocalOf<DsColors> { error("No DsColors provided") }
