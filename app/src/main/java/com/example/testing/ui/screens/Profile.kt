package com.example.testing.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testing.ui.components.CustomTextField

@Composable
fun Profile(modifier: Modifier = Modifier) {
    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomTextField()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    Profile()
}