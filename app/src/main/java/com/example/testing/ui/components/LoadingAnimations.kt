package com.example.testing.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testing.ui.theme.orange

private const val PADDING_PERCENTAGE_OUTER_CIRCLE = 0.15f
private const val PADDING_PERCENTAGE_INNER_CIRCLE = 0.3f
private const val POSITION_START_OFFSET_OUTER_CIRCLE = 90f
private const val POSITION_START_OFFSET_INNER_CIRCLE = 135f

@Composable
fun TripleOrbitLoadingAnimation(modifier: Modifier = Modifier) {

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000)
        ),
        label = ""
    )
    var width by remember {
        mutableIntStateOf(0)
    }
    Box(
        modifier = modifier
            .size(200.dp)
            .onSizeChanged { width = it.width },
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            strokeWidth = 1.dp,
            color = orange,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    rotationZ = rotation
                }
        )
        CircularProgressIndicator(
            strokeWidth = 1.dp,
            color = orange,
            modifier = Modifier
                .fillMaxSize()
                .padding(with(LocalDensity.current) {
                    (width * PADDING_PERCENTAGE_INNER_CIRCLE).toDp()
                })
                .graphicsLayer {
                    rotationZ = rotation + POSITION_START_OFFSET_INNER_CIRCLE
                }
        )
        CircularProgressIndicator(
            strokeWidth = 1.dp,
            color = orange,
            modifier = Modifier
                .fillMaxSize()
                .padding(with(LocalDensity.current) {
                    (width * PADDING_PERCENTAGE_OUTER_CIRCLE).toDp()
                })
                .graphicsLayer {
                    rotationZ = rotation + POSITION_START_OFFSET_OUTER_CIRCLE
                }
        )
    }
}


@Composable
fun PulseLoadingAnimation(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000)
        ),
        label = ""
    )
    Box(
        modifier = modifier
            .size(200.dp)
            .graphicsLayer {
                scaleX = progress
                scaleY = progress
                alpha = 1f - progress
            }
            .border(width = 5.dp, color = orange, shape = CircleShape)
    )
}


@Composable
fun BlurredAnimatedText(modifier: Modifier = Modifier, text: String) {
    val blurList = text.mapIndexed { index, character ->
        if (character == ' ') {
            remember {
                mutableFloatStateOf(0f)
            }
        } else {
            val infiniteTransition = rememberInfiniteTransition(label = "")
            infiniteTransition.animateFloat(
                initialValue = 10f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Reverse,
                    initialStartOffset = StartOffset(offsetMillis = (1000 / text.length) * index)
                ),
                label = ""
            )
        }
    }
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        text.forEachIndexed { index, character ->
            Text(
                text = character.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.graphicsLayer {
                    if (character != ' ') {
                        val blurAmount = blurList[index].value
                        renderEffect = BlurEffect(
                            radiusX = blurAmount,
                            radiusY = blurAmount
                        )
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    BlurredAnimatedText(text = "Hello World Again Here")
}