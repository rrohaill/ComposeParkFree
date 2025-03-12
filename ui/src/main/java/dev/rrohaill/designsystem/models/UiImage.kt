package dev.rrohaill.designsystem.models

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.Companion.DefaultTransform
import coil.request.NullRequestDataException
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieClipSpec
import dev.rrohaill.designsystem.DsTestTags

sealed class UiImage {

    abstract fun getTestTag(): String

    data class CoilImage(
        val model: Any?,
        val transform: (AsyncImagePainter.State) -> AsyncImagePainter.State = DefaultTransform,
        val onState: (AsyncImagePainter.State) -> Unit = {}
    ) : UiImage() {
        companion object {
            @Stable
            @Composable
            fun transformOf(
                placeholder: Painter? = null,
                error: Painter? = placeholder,
                fallback: Painter? = placeholder
            ): (AsyncImagePainter.State) -> AsyncImagePainter.State {
                return if (placeholder != null || error != null || fallback != null) {
                    { state ->
                        when (state) {
                            is AsyncImagePainter.State.Loading -> {
                                if (placeholder != null) state.copy(painter = placeholder) else state
                            }

                            is AsyncImagePainter.State.Error -> {
                                if (state.result.throwable is NullRequestDataException) {
                                    if (fallback != null) state.copy(painter = fallback) else state
                                } else {
                                    if (error != null) state.copy(painter = error) else state
                                }
                            }

                            else -> state
                        }
                    }
                } else {
                    DefaultTransform
                }
            }
        }

        override fun getTestTag(): String = "${DsTestTags.UiImagePrefix}${model.toString()}"
    }

    data class VectorImage(val imageVector: ImageVector) : UiImage() {
        override fun getTestTag(): String = "${DsTestTags.UiImagePrefix}${imageVector.name}"
    }

    data class DrawableImage(@DrawableRes val drawableRes: Int) : UiImage() {
        override fun getTestTag(): String = "${DsTestTags.UiImagePrefix}$drawableRes"
    }

    data class BitmapImage(val bitmap: Bitmap) : UiImage() {
        override fun getTestTag(): String = "${DsTestTags.UiImagePrefix}${bitmap.generationId}"

        override fun equals(other: Any?) =
            bitmap.sameAs(other as? Bitmap) // we are using bitmap recycling, so important to check if contents are the same
    }

    data class LottieImage(
        val composition: LottieComposition?,
        val iterations: Int = 1,
        val clipSpec: LottieClipSpec? = null
    ) : UiImage() {
        override fun getTestTag(): String =
            "${DsTestTags.UiImagePrefix}${composition.toString()}$iterations${clipSpec.toString()}"
    }

    data object None : UiImage() {
        override fun getTestTag(): String = "${DsTestTags.UiImagePrefix} NO IMAGE"
    }

    companion object {
        fun of(
            model: Any?,
            transform: (AsyncImagePainter.State) -> AsyncImagePainter.State = DefaultTransform,
            onState: (AsyncImagePainter.State) -> Unit = {}
        ) = CoilImage(model, transform, onState)

        fun of(@DrawableRes id: Int) = DrawableImage(id)

        fun of(bitmap: Bitmap) = BitmapImage(bitmap)

        fun of(imageVector: ImageVector) = VectorImage(imageVector)

        fun of(
            composition: LottieComposition?,
            iterations: Int = 1,
            clipSpec: LottieClipSpec? = null
        ) = LottieImage(composition, iterations, clipSpec)
    }
}

fun @receiver:DrawableRes Int.uiImage() = UiImage.DrawableImage(this)