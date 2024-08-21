package com.example.testing.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomAnimScaling(modifier: Modifier = Modifier) {

    Spacer(
        modifier
            .fillMaxWidth()
            .height(35.dp)
    )

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var isVisible by remember {
            mutableStateOf(false)
        }

        val animeSize by animateDpAsState(
            targetValue = if (isVisible) 300.dp else 100.dp,
            animationSpec = spring(
                dampingRatio = 0.3f,
                stiffness = 16f
            )


        )

        val animCorners by animateDpAsState(
            targetValue =
            if (isVisible) 300.dp else 0.dp,
            animationSpec = tween(
                durationMillis = 900,
                easing = FastOutSlowInEasing
            )
        )

        val animColor by animateColorAsState(
            targetValue =
            if (isVisible) Color.Red else Color.Green,
            animationSpec = tween(
                durationMillis = 900,
                easing = FastOutSlowInEasing
            )
        )

        val transition = rememberInfiniteTransition()
        val transColor by transition.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Blue,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 2000)
            )
        )

        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text(text = "Click")
        }

        Spacer(
            modifier
                .fillMaxWidth()
                .height(35.dp)
        )

        Column(
            modifier
                .size(animeSize)
                .background(animColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

        }
    }
}