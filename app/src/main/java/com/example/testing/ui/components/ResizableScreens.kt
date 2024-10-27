package com.example.testing.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun ResizableScreen(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.size(60.dp).background(Color.Red))
                Box(modifier = Modifier.size(60.dp).background(Color.Blue))
                Box(modifier = Modifier.size(60.dp).background(Color.Green))
                Box(modifier = Modifier.size(60.dp).background(Color.Yellow))
                Box(modifier = Modifier.size(60.dp).background(Color.Cyan))
                Box(modifier = Modifier.size(60.dp).background(Color.Magenta))
            }
        }

        else -> {
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.size(60.dp).background(Color.Red))
                Box(modifier = Modifier.size(60.dp).background(Color.Blue))
                Box(modifier = Modifier.size(60.dp).background(Color.Green))
                Box(modifier = Modifier.size(60.dp).background(Color.Yellow))
                Box(modifier = Modifier.size(60.dp).background(Color.Cyan))
                Box(modifier = Modifier.size(60.dp).background(Color.Magenta))
            }
        }
    }
}