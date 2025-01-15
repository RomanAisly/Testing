package com.example.testing.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testing.ui.components.ExpandableText

@Composable
fun Settings(modifier: Modifier = Modifier) {

    val sampleText =
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum"
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        ExpandableText(text = sampleText, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Settings()
}