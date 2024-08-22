package com.example.testing.ui.components

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
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnim(modifier: Modifier = Modifier) {

    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.Asset("downloading.json"))
    var isPlaying by remember {
        mutableStateOf(false)

    }
    var isComplete by remember {
        mutableStateOf(false)
    }
    val animSpec = LottieClipSpec.Progress(
        0f,
        if (isComplete) {
            1f
        } else {
            0.9f
        }
    )

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = lottieComposition,
            isPlaying = isPlaying,
            iterations = if (isComplete) {
                1
            } else {
                LottieConstants.IterateForever
            },
            reverseOnRepeat = true,
            clipSpec = animSpec,
            modifier = modifier.size(200.dp)
        )

        Spacer(
            modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        Button(onClick = { isPlaying = true }) {
            Text(text = "Download")
        }

        Spacer(
            modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        Button(onClick = { isComplete = true }) {
            Text(text = "Finish")
        }

        Spacer(
            modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        Button(onClick = { isComplete = false }) {
            Text(text = "stop")
        }
    }
}