package com.example.testing.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
fun CustomAnimVisibility(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var isVisible by remember {
            mutableStateOf(false)
        }

        Button(
            onClick = { isVisible = !isVisible }
        ) {
            Text(text = "Click")
        }

        Column(
            modifier
                .size(300.dp)
                .background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn() + slideInHorizontally(
                    spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    initialOffsetX = { -it }),

                exit = fadeOut() + slideOutHorizontally(
                    targetOffsetX = { it })
            ) {
                Box(
                    modifier
                        .size(200.dp)
                        .background(Color.Red)
                )
            }
        }

        Column(
            modifier
                .size(300.dp)
                .padding(top = 30.dp)
                .background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedContent(
                targetState = isVisible,
                modifier
                    .size(260.dp),
                transitionSpec = {
                    (fadeIn() + slideInHorizontally(
                        animationSpec =
                        tween(durationMillis = 1500),
                        initialOffsetX = {
                            it
                        }
                    )).togetherWith(
                        fadeOut() + slideOutHorizontally(
                            animationSpec =
                            tween(durationMillis = 1500),
                            targetOffsetX = {
                                -it
                            }
                        )
                    )
                }
            ) {
                if (it) {
                    Box(modifier.background(Color.Green))
                } else {
                    Box(modifier.background(Color.Yellow))
                }
            }
        }
    }
}