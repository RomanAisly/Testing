package com.example.testing.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testing.ui.components.HeartCandy
import com.example.testing.ui.components.MeshGrad
import com.example.testing.ui.components.MeshGradientButton
import com.example.testing.ui.components.NonBinaryHearts
import com.example.testing.ui.components.shimmerLoading

@Composable
fun Settings(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        MeshGradientButton()
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {

}