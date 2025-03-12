package dev.rrohaill.designsystem.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * The design system colors. Colors prefix with 'd' is short for dark.
 * Keeping this prefix to be in harmony with design system color names
 *
 * BEWARE! These colors should NOT be referenced directly in UI code. Only from DsColors or themes.
 */
internal object DesignSystemColors {
    // Primary
    val Green100 = Color(0xFFEDF9E1)
    val Green150 = Color(0xFFD4F0B4)
    val Green300 = Color(0xFF76C82E)
    val Green400 = Color(0xFF178000)
    val Green500 = Color(0xFF003004)
    val dGreen100 = Color(0xFF18361A)
    val dGreen150 = Color(0xFF264A26)
    val dGreen300 = Color(0xFF6FBD2B)
    val dGreen400 = Color(0xFFA2C777)
    val dGreen500 = Color(0xFFD0E8B5)

    // Red
    val Red100 = Color(0xFFF0CCD1)
    val Red400 = Color(0xFFB50019)
    val dRed300 = Color(0xFFF53649)
    val dRed400 = Color(0xFFD42A3B)
    val dRed500 = Color(0xFFB50019)

    // Amber
    val Amber050 = Color(0xFFE5D4CA)
    val Amber100 = Color(0xFFFFF6D7)
    val Amber150 = Color(0xFFF5DBA5)
    val Amber200 = Color(0xFFFFDE9B)
    val Amber300 = Color(0xFFFEA628)
    val Amber400 = Color(0xFFBD500C)
    val Amber500 = Color(0xFF551903)
    val dAmber050 = Color(0xFF3D312B)
    val dAmber100 = Color(0xFF3D180B)
    val dAmber200 = Color(0xFF82452A)
    val dAmber300 = Color(0xFFD18E2E)
    val dAmber400 = Color(0xFFE5BB70)
    val dAmber500 = Color(0xFFFAE8BB)

    // Indigo
    val Indigo100 = Color(0xFFF1F4FF)
    val Indigo200 = Color(0xFFBEC4FF)
    val Indigo300 = Color(0xFF600AFF)
    val Indigo400 = Color(0xFF3B0AAF)
    val Indigo500 = Color(0xFF14035E)
    val dIndigo100 = Color(0xFF2A2659)
    val dIndigo200 = Color(0xFF484599)
    val dIndigo300 = Color(0xFF5747D1)
    val dIndigo400 = Color(0xFFA7ADE5)
    val dIndigo500 = Color(0xFFD6DFFA)

    // Grey
    val Grey100 = Color(0xFF4F4C4A)
    val Grey200 = Color(0xFF292724)
    val Grey300 = Color(0xFF22201C)
    val Grey400 = Color(0xFF1A1815)
    val Grey500 = Color(0xFF12100D)

    // Beige
    val Beige100 = Color(0xFFF8F6F3)
    val Beige200 = Color(0xFFEEEBE8)
    val Beige300 = Color(0xFFE4E1DD)
    val Beige400 = Color(0xFFDBD6D1)
    val Beige500 = Color(0xFF938E8A)

    // Black
    val Black = Color(0xFF000000)
    val Black65 = Color(0xA5000000)

    // White
    val White = Color(0xFFFFFFFF)
    val White8 = Color(0x14FFFFFF)
    val White65 = Color(0xA6FFFFFF)

    // TODO Stopid material 3 library don't allow using alphas
    val NavigationBarIndicatorBackground = Color(0xFF25271A)
    val SplashBackground = Color(0xFFACD77B)
    val SplashBackgroundDark = Color(0xFF012B03)

    val BankIdBlue = Color(0xFF193E4F)
}