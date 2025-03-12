package dev.rrohaill.designsystem.ui.theme

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Amber050
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Amber100
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Amber150
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Amber200
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Amber300
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Amber400
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Amber500
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.BankIdBlue
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Beige100
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Beige200
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Beige300
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Beige400
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Beige500
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Black65
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Green100
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Green150
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Green300
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Green400
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Green500
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Grey100
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Grey200
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Grey300
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Grey500
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Indigo100
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Indigo200
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Indigo300
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Indigo400
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Indigo500
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.NavigationBarIndicatorBackground
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Red100
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.Red400
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.SplashBackground
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.SplashBackgroundDark
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.White
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.White65
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.White8
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dAmber050
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dAmber100
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dAmber200
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dAmber300
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dAmber400
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dAmber500
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dGreen100
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dGreen150
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dGreen300
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dGreen400
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dGreen500
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dIndigo100
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dIndigo200
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dIndigo300
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dIndigo400
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dIndigo500
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dRed300
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dRed400
import dev.rrohaill.designsystem.ui.theme.DesignSystemColors.dRed500
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsDimensions
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsElevations
import dev.rrohaill.designsystem.ui.theme.DsTheme.dsTypography

object DsPalette {
    val light = DsColors(
        textPrimary = Grey300,
        textSecondary = Black65,
        textBlack = Grey300,
        textWhite = Beige100,
        textInverted = Beige100,
        backgroundPrimary = Beige100,
        backgroundSecondary = White,
        backgroundTertiary = White,
        backgroundInverted = Grey500,
        backgroundWhite = White,
        surfaceNeutral = Beige200,
        borderDistinct = Beige400,
        borderSubtle = Beige200,
        borderInput = Beige500,
        buttonPrimary = Green400,
        buttonBorder = Black65,
        buttonDismissive = Red400,
        linkPrimary = Green400,
        activeHighlight = Green400,
        bottomNavigationHighlight = Green100,
        notification = Amber400,
        surfaceSuccess = Green150,
        onSurfaceSuccess = Green500,
        surfaceHighlight = Amber150,
        onSurfaceHighlight = Amber500,
        surfaceInfo = Indigo200,
        onSurfaceInfo = Indigo500,
        surfaceError = Red400,
        onSurfaceError = Beige100,
        surfaceErrorLight = Red100,
        onSurfaceErrorLight = Red400,
        errorText = Red400,
        disabledSurface = Beige300,
        onDisabledSurface = Black65,
        disabledBorder = Beige400,
        surfaceTint = White,
        sharedSurface = Amber050,
        onSharedSurface = Amber500,
        bankIdBlue = BankIdBlue,
        green100 = Green100,
        green150 = Green150,
        green300 = Green300,
        green400 = Green400,
        green500 = Green500,
        amber100 = Amber100,
        amber200 = Amber200,
        amber300 = Amber300,
        amber400 = Amber400,
        amber500 = Amber500,
        indigo100 = Indigo100,
        indigo200 = Indigo200,
        indigo300 = Indigo300,
        indigo400 = Indigo400,
        indigo500 = Indigo500,
        splashBackground = SplashBackground,
    )
    val dark = DsColors(
        textPrimary = Beige100,
        textSecondary = White65,
        textBlack = Grey300,
        textWhite = Beige100,
        textInverted = Grey300,
        backgroundPrimary = Grey500,
        backgroundSecondary = Grey500,
        backgroundTertiary = Grey300,
        backgroundInverted = Beige100,
        backgroundWhite = White,
        surfaceNeutral = White8,
        borderDistinct = Grey100,
        borderSubtle = Grey200,
        borderInput = Beige500,
        buttonPrimary = dGreen300,
        buttonBorder = White65,
        buttonDismissive = dRed400,
        linkPrimary = dGreen300,
        activeHighlight = dGreen300,
        bottomNavigationHighlight = NavigationBarIndicatorBackground,
        notification = dAmber400,
        surfaceSuccess = dGreen150,
        onSurfaceSuccess = dGreen500,
        surfaceHighlight = dAmber200,
        onSurfaceHighlight = dAmber500,
        surfaceInfo = dIndigo200,
        onSurfaceInfo = dIndigo500,
        surfaceError = dRed400,
        onSurfaceError = Beige100,
        surfaceErrorLight = dRed500,
        onSurfaceErrorLight = Beige100,
        errorText = dRed300,
        disabledSurface = Grey200,
        onDisabledSurface = White65,
        disabledBorder = Grey100,
        surfaceTint = dGreen400,
        sharedSurface = dAmber050,
        onSharedSurface = dAmber500,
        bankIdBlue = Beige100,
        green100 = dGreen100,
        green150 = dGreen150,
        green300 = dGreen300,
        green400 = dGreen400,
        green500 = dGreen500,
        amber100 = dAmber100,
        amber200 = dAmber200,
        amber300 = dAmber300,
        amber400 = dAmber400,
        amber500 = dAmber500,
        indigo100 = dIndigo100,
        indigo200 = dIndigo200,
        indigo300 = dIndigo300,
        indigo400 = dIndigo400,
        indigo500 = dIndigo500,
        splashBackground = SplashBackgroundDark,
    )
}

private object DsMaterialScope {
    fun getLightColorScheme(dsColors: DsColors): ColorScheme {
        return lightColorScheme(
            background = dsColors.backgroundPrimary,
            error = dsColors.errorText,
            onBackground = dsColors.textBlack,
            onError = dsColors.onSurfaceError,
            onPrimary = dsColors.textInverted,
            onPrimaryContainer = dsColors.textPrimary,
            onSecondary = dsColors.textBlack,
            onSurface = dsColors.textBlack,
            outline = dsColors.borderInput,
            primary = dsColors.buttonPrimary,
            secondary = dsColors.activeHighlight,
            surface = dsColors.backgroundSecondary,
            surfaceContainerLow = dsColors.backgroundTertiary,
            surfaceContainer = dsColors.backgroundTertiary,
            surfaceTint = dsColors.surfaceTint,
            surfaceVariant = dsColors.surfaceNeutral,
        )
    }

    fun getDarkColorScheme(dsColors: DsColors): ColorScheme {
        return darkColorScheme(
            background = dsColors.backgroundPrimary,
            error = dsColors.errorText,
            onBackground = dsColors.textWhite,
            onError = dsColors.onSurfaceError,
            onPrimary = dsColors.textInverted,
            onPrimaryContainer = dsColors.textPrimary,
            onSecondary = dsColors.textBlack,
            onSurface = dsColors.textWhite,
            outline = dsColors.borderInput,
            primary = dsColors.buttonPrimary,
            secondary = dsColors.activeHighlight,
            surface = dsColors.backgroundSecondary,
            surfaceContainerLow = dsColors.backgroundTertiary,
            surfaceContainer = dsColors.backgroundTertiary,
            surfaceTint = dsColors.surfaceTint,
            surfaceVariant = dsColors.surfaceNeutral,
        )
    }

    @Composable
    fun createTypography(): androidx.compose.material3.Typography {
        return androidx.compose.material3.Typography(
            displayLarge = dsTypography.DisplayL,
            displayMedium = dsTypography.DisplayL,
            displaySmall = dsTypography.DisplayL,
            headlineLarge = dsTypography.DisplayM,
            headlineMedium = dsTypography.HeadingL,
            headlineSmall = dsTypography.HeadingM,
            titleLarge = dsTypography.HeadingM,
            titleMedium = dsTypography.TitleM,
            titleSmall = dsTypography.TitleS,
            labelSmall = dsTypography.Overline,
            labelMedium = dsTypography.ButtonS,
            labelLarge = dsTypography.ButtonM,
            bodyLarge = dsTypography.BodyM,
            bodyMedium = dsTypography.BodyS,
            bodySmall = dsTypography.Caption,
        )
    }
}

/**
 * Provides the app specific compositions to the theme
 */
@Composable
private fun ProvideDsTheme(
    colors: DsColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(
        LocalDsTypography provides dsTypography,
        LocalDsColors provides colorPalette,
        LocalDsDimensions provides dsDimensions,
        LocalDsElevations provides dsElevations,
        LocalTextStyle provides dsTypography.BodyM,
        content = content
    )
}

@Composable
fun DesignSystemTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val dsColors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && dynamicColor) {
        getDynamicColorScheme(darkTheme, context)
    } else {
        if (darkTheme) DsPalette.dark else DsPalette.light
    }
    val materialColors = if (darkTheme) {
        DsMaterialScope.getDarkColorScheme(dsColors)
    } else {
        DsMaterialScope.getLightColorScheme(dsColors)
    }

    ProvideDsTheme(dsColors) {
        MaterialTheme(
            colorScheme = materialColors,
            typography = DsMaterialScope.createTypography(),
            content = content
        )
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
private fun getDynamicColorScheme(
    darkTheme: Boolean,
    context: Context
) = if (darkTheme.not()) {
    val dynamicColorScheme = dynamicLightColorScheme(context)
    DsPalette.light.copy(
        backgroundPrimary = dynamicColorScheme.surfaceColorAtElevation(1.dp),
        activeHighlight = dynamicColorScheme.secondary,
        buttonPrimary = dynamicColorScheme.primary,
        bottomNavigationHighlight = dynamicColorScheme.secondaryContainer,
        isDynamicTheme = true,
    )
} else {
    val dynamicColorScheme = dynamicDarkColorScheme(context)
    DsPalette.dark.copy(
        backgroundSecondary = dynamicColorScheme.surface,
        surfaceTint = dynamicColorScheme.surfaceTint,
        backgroundPrimary = dynamicColorScheme.background,
        activeHighlight = dynamicColorScheme.secondary,
        buttonPrimary = dynamicColorScheme.primary,
        bottomNavigationHighlight = dynamicColorScheme.secondaryContainer,
        isDynamicTheme = true,
    )
}

/**
 * Alternate to [MaterialTheme] allowing us to add our own theme systems (e.g. [DsTypography]) or to
 * extend [MaterialTheme]'s types e.g. return our own [Colors] extension
 */
object DsTheme {

    /**
     * Retrieves the current [DsShapes] at the call site's position in the hierarchy.
     */
    val dsShapes: DsShapes
        @Composable
        get() = LocalDsShapes.current

    /**
     * Retrieves the current [DsTypography] at the call site's position in the hierarchy.
     */
    val dsTypography: DsTypography
        @Composable
        get() = LocalDsTypography.current

    /**
     * Retrieves the current [DsTypography] at the call site's position in the hierarchy.
     */
    val dsColors: DsColors
        @Composable
        get() = LocalDsColors.current

    /**
     * Retrieves the current [DsDimensions] at the call site's position in the hierarchy.
     */
    val dsDimensions: DsDimensions
        @Composable
        get() = LocalDsDimensions.current

    /**
     * Retrieves the current [DsElevations] at the call site's position in the hierarchy.
     */
    val dsElevations: DsElevations
        @Composable
        get() = LocalDsElevations.current
}