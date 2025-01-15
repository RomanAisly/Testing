package com.example.testing.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.testing.R
import com.example.testing.ui.screens.Profile

@Composable
fun CustomTextField(modifier: Modifier = Modifier) {

    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        supportingText = {
            if (text.length > 100) {
                Text(text = "Text is too long")
                isError = true
            } else {
                isError = false
            }
        },
        isError = isError,
        leadingIcon = {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.youtubevec),
                contentDescription = ""
            )
        },
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface),
        modifier = modifier
    )

}

@Composable
fun PhoneField(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    mask: String = "999 999 99 99",
    maskNumber: Char = '9',
    onPhoneNumberChanged: (String) -> Unit
) {
    TextField(
        value = phoneNumber,
        onValueChange = { it ->
            if (it.length <= 10) {
                onPhoneNumberChanged(it.take(mask.count { it == maskNumber }))
            }
        },
        label = { Text(text = "Phone number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = PhoneVisualTransformation(mask, maskNumber),
        modifier = modifier.fillMaxWidth()
    )
}

class PhoneVisualTransformation(val mask: String, val maskNumber: Char) : VisualTransformation {
    private val maxLength = mask.count { it == maskNumber }
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text
        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString
            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }
        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        if (mask != other.mask) return false
        if (maskNumber != other.maskNumber) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}

class PhoneOffsetMapper(private val mask: String, private val maskNumber: Char) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            if (mask[i++] != maskNumber) noneDigitCount++
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int =
        offset - mask.take(offset).count { it != maskNumber }
}