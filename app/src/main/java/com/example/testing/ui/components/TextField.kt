package com.example.testing.ui.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testing.ui.screens.Profile

@Composable
fun TextField(modifier: Modifier = Modifier) {

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
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
   Profile()
}