package dev.rrohaill.designsystem.standard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import dev.rrohaill.designsystem.models.UiImage
import dev.rrohaill.designsystem.models.UiImage.CoilImage.Companion.transformOf
import dev.rrohaill.designsystem.DsSpace
import dev.rrohaill.designsystem.text.UiText
import dev.rrohaill.designsystem.text.uiText
import dev.rrohaill.designsystem.ui.theme.DesignSystemTheme
import dev.rrohaill.designsystem.util.DsPreviews
import dev.rrohaill.parkfree.R


sealed class IconSize(val size: Dp) {

    data object Default : IconSize(24.dp)
    data object Large : IconSize(32.dp)
    data object Small : IconSize(16.dp)
}

@SuppressLint("DesignSystemMaterialReplacementComposable")
@Composable
fun DsIcon(
    icon: UiImage,
    modifier: Modifier = Modifier,
    iconSize: IconSize = IconSize.Default,
    contentDescription: UiText? = null,
    tint: Color = LocalContentColor.current
) {
    val contentDescriptionValue = contentDescription?.getString(LocalContext.current)
    when (icon) {
        is UiImage.CoilImage -> {
            Icon(
                painter = rememberAsyncImagePainter(
                    model = icon.model,
                    transform = icon.transform,
                    onState = icon.onState
                ),
                contentDescription = contentDescriptionValue,
                modifier = modifier
                    .testTag(icon.getTestTag())
                    .size(iconSize.size),
                tint = tint
            )
        }

        is UiImage.DrawableImage -> {
            Icon(
                painter = painterResource(id = icon.drawableRes),
                contentDescription = contentDescriptionValue,
                modifier = modifier
                    .testTag(icon.getTestTag())
                    .size(iconSize.size),
                tint = tint
            )
        }

        is UiImage.BitmapImage -> {
            Icon(
                bitmap = icon.bitmap.asImageBitmap(),
                contentDescription = contentDescriptionValue,
                modifier = modifier
                    .testTag(icon.getTestTag())
                    .size(iconSize.size),
                tint = tint
            )
        }

        is UiImage.VectorImage -> {
            Icon(
                imageVector = icon.imageVector,
                contentDescription = contentDescriptionValue,
                modifier = modifier
                    .testTag(icon.getTestTag())
                    .size(iconSize.size),
                tint = tint
            )
        }

        is UiImage.LottieImage -> {
            throw IllegalStateException("Not supported in material icon")
        }

        UiImage.None -> {

        }
    }
}


@DsPreviews
@Composable
fun PreviewDsIcon() {
    DesignSystemTheme {
        DsSurface {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DsIcon(
                    icon = UiImage.of(R.drawable.arrow_left),
                    contentDescription = "Icon from material".uiText()
                )
                DsSpace.default()
                DsIcon(
                    icon = UiImage.of(R.drawable.ic_person),
                    contentDescription = "File from drawable".uiText()
                )
                DsSpace.default()
                DsIcon(
                    icon = UiImage.of(
                        model = "",
                        transform = transformOf(placeholder = painterResource(R.drawable.ic_launcher_foreground)),
                    ),
                    contentDescription = "coil local file".uiText()
                )
            }
        }
    }
}