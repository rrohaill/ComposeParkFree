package dev.rrohaill.designsystem.text

import android.content.Context
import androidx.annotation.IdRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TtsAnnotation
import androidx.compose.ui.text.buildAnnotatedString

/**
 * Contains all of the different ways string texts can be presented to the UI in Compose and Android combined
 * TODO: Move to models
 */
sealed interface UiText {
    fun getString(context: Context): String

    fun getTestTag(): String

    companion object {

        fun of(value: String) = StringText(value)

        fun of(@StringRes id: Int, vararg stringValues: Any) = ResourceText(id, *stringValues)

        fun of(@PluralsRes id: Int, quantity: Int, vararg stringValues: Any) =
            PluralText(id, quantity, *stringValues)

        fun of(value: AnnotatedString) = AnnotatedText(value)

        val empty = StringText("")
    }
}

data class StringText(val value: String) : UiText {
    override fun getString(context: Context) = value
    override fun getTestTag() = value
}

data class UppercaseText(val value: Int) : UiText {
    override fun getString(context: Context) = context.resources.getString(value).uppercase()
    override fun getTestTag() = value.toString()
}

class ResourceText(@StringRes val value: Int, private vararg val stringValues: Any) : UiText {
    override fun getString(context: Context) = context.getString(value, *stringValues)
    override fun getTestTag() = StringBuilder(value.toString()).append(*stringValues).toString()
    override fun hashCode(): Int {
        return value
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is ResourceText -> {
                value == other.value && stringValues.contentEquals(other.stringValues)
            }

            else -> false
        }
    }
}

class PluralText(
    @PluralsRes val value: Int,
    val quantity: Int,
    private vararg val stringValues: Any
) : UiText {
    override fun getString(context: Context) =
        context.resources.getQuantityString(value, quantity, *stringValues)

    override fun getTestTag() =
        StringBuilder(value.toString()).append(quantity).append(*stringValues).toString()
}

class AnnotatedText(val value: AnnotatedString, private val customTestTag: String = value.text) :
    UiText, Comparable<AnnotatedString> {
    override fun getString(context: Context): String {
        return value.toString()
    }

    override fun getTestTag() = customTestTag

    override fun compareTo(other: AnnotatedString): Int {
        return value.toString().compareTo(other.toString())
    }
}

sealed class AnnotatedStringValue(
    val value: Any,
) {
    class WithSpanStyle(value: Any, val style: SpanStyle) : AnnotatedStringValue(value)
    class WithTtsAnnotation(value: Any, val annotation: TtsAnnotation) : AnnotatedStringValue(value)
}

private data class AnnotatedSpanData(
    val type: AnnotatedStringValue,
    val startPosition: Int,
    val endPosition: Int
)

@OptIn(ExperimentalTextApi::class)
fun UiText.toAnnotatedText(
    context: Context,
    vararg annotatedStringValues: AnnotatedStringValue
): AnnotatedText {
    val stringToDecorate = this.getString(context)
    val spanPositions = annotatedStringValues.map {
        val word = it.value.toString()
        val startPosition = stringToDecorate.indexOf(word)
        val endPosition = startPosition + word.length
        AnnotatedSpanData(it, startPosition, endPosition)
    }
    val annotatedString = buildAnnotatedString {
        append(stringToDecorate)
        spanPositions.forEach {
            if (it.startPosition >= 0 && it.endPosition <= stringToDecorate.length) {
                when (it.type) {
                    is AnnotatedStringValue.WithSpanStyle -> {
                        addStyle(it.type.style, it.startPosition, it.endPosition)
                    }

                    is AnnotatedStringValue.WithTtsAnnotation -> {
                        addTtsAnnotation(it.type.annotation, it.startPosition, it.endPosition)
                    }
                }

            }
        }
    }
    return AnnotatedText(annotatedString, this.getTestTag())
}

@Composable
fun UiText.toAnnotatedText(vararg annotatedStringValues: AnnotatedStringValue): AnnotatedText {
    return this.toAnnotatedText(LocalContext.current, *annotatedStringValues)
}

fun String.uiText() = StringText(this)

fun @receiver:IdRes Int.uiText() = ResourceText(this)

@Composable
fun UiText.getString(): String {
    return this.getString(LocalContext.current)
}

@Composable
fun UiText.isEmpty(): Boolean {
    return getString().isEmpty()
}

@Composable
fun UiText.isNotEmpty(): Boolean {
    return getString().isNotEmpty()
}

fun StringBuilder.toStringText(): StringText = StringText(this.toString())