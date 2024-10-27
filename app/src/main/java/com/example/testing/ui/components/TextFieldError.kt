package com.example.testing.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.testing.R
import com.example.testing.ui.screens.Profile

@Composable
fun TextFieldError(modifier: Modifier = Modifier) {

    var text by remember {
        mutableStateOf("")
    }

    var isError by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        supportingText = {
            if (text.length > 10) {
                Text(text = "Text is too long")
                isError = true
            } else {
                isError = false
            }
        },
        isError = isError,
        leadingIcon = {
            Image(imageVector = ImageVector.vectorResource(id = R.drawable.youtubevec), contentDescription = "")
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Profile()
}