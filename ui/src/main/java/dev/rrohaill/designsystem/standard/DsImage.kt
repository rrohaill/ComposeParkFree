package dev.rrohaill.designsystem.standard

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.rrohaill.designsystem.DsSpace
import dev.rrohaill.designsystem.models.UiImage
import dev.rrohaill.designsystem.models.UiImage.CoilImage.Companion.transformOf
import dev.rrohaill.designsystem.text.UiText
import dev.rrohaill.designsystem.text.uiText
import dev.rrohaill.designsystem.ui.theme.DesignSystemTheme
import dev.rrohaill.parkfree.R

@SuppressLint("DesignSystemMaterialReplacementComposable")
@Composable
fun DsImage(
    modifier: Modifier = Modifier,
    image: UiImage,
    contentDescription: UiText? = null,
    colorFilter: ColorFilter? = null,
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    alpha: Float = DefaultAlpha,
) {
    val contentDescriptionValue = contentDescription?.getString(LocalContext.current)
    when (image) {
        is UiImage.CoilImage -> {
            AsyncImage(
                model = image.model,
                contentDescription = contentDescriptionValue,
                modifier = modifier
                    .testTag(image.getTestTag()),
                transform = image.transform,
                onState = image.onState,
                colorFilter = colorFilter,
                contentScale = contentScale,
                alpha = alpha,
                alignment = alignment
            )
        }

        is UiImage.DrawableImage -> {
            Image(
                painter = painterResource(id = image.drawableRes),
                contentDescription = contentDescriptionValue,
                modifier = modifier
                    .testTag(image.getTestTag()),
                colorFilter = colorFilter,
                contentScale = contentScale,
                alpha = alpha,
                alignment = alignment
            )
        }

        is UiImage.BitmapImage -> {
            Image(
                bitmap = image.bitmap.asImageBitmap(),
                contentDescription = contentDescriptionValue,
                modifier = modifier
                    .testTag(image.getTestTag()),
                colorFilter = colorFilter,
                contentScale = contentScale,
                alpha = alpha,
                alignment = alignment
            )
        }

        is UiImage.VectorImage -> {
            Image(
                imageVector = image.imageVector,
                contentDescription = contentDescriptionValue,
                modifier = modifier
                    .testTag(image.getTestTag()),
                colorFilter = colorFilter,
                contentScale = contentScale,
                alpha = alpha,
                alignment = alignment
            )
        }

        is UiImage.LottieImage -> {
            val semantics = if (contentDescriptionValue != null) {
                Modifier.semantics {
                    this.contentDescription = contentDescriptionValue
                    this.role = Role.Image
                }
            } else {
                Modifier
            }
            LottieAnimation(
                composition = image.composition,
                iterations = image.iterations,
                modifier = modifier
                    .testTag(image.getTestTag())
                    .then(semantics),
                clipSpec = image.clipSpec,
                contentScale = contentScale,
                alignment = alignment
            )
        }

        is UiImage.None -> {

        }
    }
}

@Preview(locale = "en")
@Composable
fun PreviewDsImage() {
    DesignSystemTheme {
        DsSurface {
            Column {
                val solidRedBlock =
                    "{\"v\":\"5.5.2\",\"fr\":0,\"ip\":0,\"op\":60,\"w\":56,\"h\":56,\"ddd\":0,\"" +
                            "assets\":[],\"fonts\":{\"list\":[]},\"" +
                            "layers\":[{\"ty\":1,\"ip\":0,\"op\":60,\"st\":0,\"ks\":{},\"" +
                            "sc\":\"#ff0000\",\"sh\":512,\"sw\":512}]}"

                val lottieComposition by rememberLottieComposition(
                    LottieCompositionSpec.JsonString(
                        solidRedBlock
                    )
                )

                DsImage(
                    Modifier.size(56.dp),
                    image = UiImage.DrawableImage(R.drawable.ic_launcher_foreground),
                    contentDescription = "File from drawable".uiText()
                )
                DsSpace.default()
                DsImage(
                    modifier = Modifier.size(56.dp),
                    image = UiImage.CoilImage(
                        model = "",
                        transform = transformOf(placeholder = painterResource(R.drawable.ic_launcher_foreground)),
                    ),
                    contentDescription = "coil local file".uiText()
                )
                DsSpace.default()
                DsImage(
                    Modifier.size(56.dp),
                    image = UiImage.LottieImage(lottieComposition),
                    contentDescription = "Lottie file".uiText()
                )
            }
        }
    }
}