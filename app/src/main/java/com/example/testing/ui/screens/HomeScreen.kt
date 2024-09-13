package com.example.testing.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.testing.ui.components.CustomIndicator

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    var value by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomIndicator(
            indicatorValue = value,
        )
        TextField(
            value = value.toString(),
            onValueChange = { value = if (it.isNotEmpty()) it.toInt() else 0 },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    HomeScreen()
}