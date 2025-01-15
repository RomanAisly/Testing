package com.example.testing.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String,
    collapsedMaxLines: Int = 3,
    showMoreText: String = " show more",
    showLessText: String = " show less",
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    showMoreTextStyle: SpanStyle = SpanStyle(color = Color.Blue, fontWeight = FontWeight.SemiBold),
    showLessTextStyle: SpanStyle = SpanStyle(color = Color.Blue, fontWeight = FontWeight.SemiBold)
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isClickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableIntStateOf(0) }

    val annotatedText = buildAnnotatedString {
        if (isClickable) {
            if (isExpanded) {
                append(text)
                withLink(
                    link = LinkAnnotation.Clickable(
                        tag = "",
                        linkInteractionListener = { isExpanded = !isExpanded })
                ) {
                    withStyle(style = showLessTextStyle) {
                        append(showLessText)
                    }
                }

            } else {
                val adjustedText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreText.length).dropWhile { it.isWhitespace() || it == '.' }
                append(adjustedText)
                withLink(
                    link = LinkAnnotation.Clickable(
                        tag = "",
                        linkInteractionListener = { isExpanded = isExpanded.not() })
                ) {
                    withStyle(style = showMoreTextStyle) {
                        append(showMoreText)
                    }
                }
            }
        } else {
            append(text)
        }
    }

    Text(
        text = annotatedText,
        color = textColor,
        style = textStyle,
        maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLines,
        onTextLayout = { textLayoutResult ->
            if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                isClickable = true
                lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLines - 1)
            }
        },
        modifier = modifier
    )
}