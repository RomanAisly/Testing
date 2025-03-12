package com.example.testing.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.toPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeartCandy() {
    val textMeasurer = rememberTextMeasurer()
    var dragPosition by remember { mutableStateOf(Offset.Zero) }

    Column(
        modifier =
            Modifier
                .background(HeartCandy.backgroundColor, shape = HeartCandy.shape)
                .padding(HeartCandy.padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier =
                Modifier
                    .width(HeartCandy.Canvas.width)
                    .height(HeartCandy.Canvas.height)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                dragPosition = Offset.Zero
                            },
                        ) { change, _ ->
                            dragPosition = dragPosition.copy(
                                x = change.position.x,
                                y = change.position.y
                            )
                        }
                    },
        ) {
            val heartSize = HeartCandy.Heart.size
            val verticalPadding =
                calculatePadding(HeartCandy.Canvas.height, HeartCandy.Heart.size.height.toDp(), 4)
            val horizontalPadding =
                calculatePadding(HeartCandy.Canvas.width, HeartCandy.Heart.size.width.toDp(), 4)


            HeartCandy.hearts
                .chunked(4)
                .mapIndexed { row, colors ->
                    colors.mapIndexed { index, (color, text) ->
                        drawCandyHeart(
                            topLeft = Offset(
                                x = index * (heartSize.width + horizontalPadding),
                                y = row * (heartSize.height + verticalPadding),
                            ),
                            heartSize = HeartCandy.Heart.size,
                            color = color,
                            text = textMeasurer.measure(
                                text = text.uppercase(),
                                style = HeartCandy.Heart.textStyle,
                            ),
                            dragPosition = dragPosition
                        )
                    }
                }
        }
    }
}

private fun DrawScope.calculatePadding(fullDimension: Dp, heartDimension: Dp, items: Int): Float =
    ((fullDimension - heartDimension * items) / (items - 1)).toPx()

private fun DrawScope.drawCandyHeart(
    topLeft: Offset,
    heartSize: Size,
    color: Color,
    text: TextLayoutResult,
    dragPosition: Offset,
) {
    val paths = HeartCandy.Heart
        .pathStrings(color)
        .map { (pathString, pathColor) ->
            val path = PathParser()
                .parsePathString(pathString)
                .toNodes()
                .map { path -> path.scaleTo(heartSize.height) }
                .toPath()
                .apply {
                    translate(topLeft)
                }

            Pair(path, pathColor)
        }

    val pathBounds = paths.map { it.first.getBounds() }
    val isSelected = pathBounds.any { it.contains(dragPosition) }
    val scaleAmount = if (isSelected) 1.05f else 1f
    val textColor = if (isSelected) HeartCandy.highlightColor else color

    scale(scaleAmount) {
        paths
            .map { (path, color) ->
                drawPath(
                    path = path,
                    color = color,
                )
                drawPath(
                    path = path,
                    color = color,
                    style = Stroke(width = 2f),
                )
            }

        rotate(
            degrees = -7f,
            pivot = Offset(
                x = topLeft.x + heartSize.width * 0.5f,
                y = topLeft.y + heartSize.height * 0.5f,
            ),
        ) {

            drawText(
                textLayoutResult = text,
                topLeft = calculateTextTopLeft(
                    topLeft = topLeft,
                    text = text,
                    heartWidth = heartSize.width,
                    padding = 5.dp.toPx()
                ),
                color = textColor,
            )
        }
    }
}

private fun calculateTextTopLeft(
    topLeft: Offset,
    text: TextLayoutResult,
    heartWidth: Float,
    padding: Float
) = Offset(
    y = topLeft.y + (text.size.height * (1f / text.lineCount) + padding),
    x = topLeft.x + heartWidth * 0.5f - text.size.width * 0.3f,
)

private fun PathNode.scaleTo(height: Float): PathNode {
    val size =
        Size(
            width = HeartCandy.Heart.width,
            height = height,
        )
    return when (this) {
        is PathNode.CurveTo ->
            this.copy(
                x1 = x1.scaleToSize(HeartCandy.Heart.originalWidth, size.width),
                x2 = x2.scaleToSize(HeartCandy.Heart.originalWidth, size.width),
                x3 = x3.scaleToSize(HeartCandy.Heart.originalWidth, size.width),
                y1 = y1.scaleToSize(HeartCandy.Heart.originalHeight, size.height),
                y2 = y2.scaleToSize(HeartCandy.Heart.originalHeight, size.height),
                y3 = y3.scaleToSize(HeartCandy.Heart.originalHeight, size.height),
            )

        is PathNode.MoveTo ->
            this.copy(
                x = x.scaleToSize(HeartCandy.Heart.originalWidth, size.width),
                y = y.scaleToSize(HeartCandy.Heart.originalHeight, size.height),
            )

        is PathNode.LineTo ->
            this.copy(
                x = x.scaleToSize(HeartCandy.Heart.originalWidth, size.width),
                y = y.scaleToSize(HeartCandy.Heart.originalHeight, size.height),
            )

        else -> this
    }
}

private fun Float.scaleToSize(
    oldSize: Float,
    newSize: Float,
): Float {
    val ratio = newSize / oldSize
    return this * ratio
}

object HeartCandy {
    val padding = 16.dp
    val shape = RoundedCornerShape(12.dp)
    val backgroundColor = Color(0xffffe4d6)

    object Canvas {
        val width = 320.dp
        val height = 300.dp
    }

    object Heart {
        val originalWidth = 201f
        val originalHeight = 187f
        private val ratio = originalWidth / originalHeight
        val textStyle = TextStyle.Default.copy(
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            fontSize = 10.sp,
        )

        val height = 170f
        val width = height * ratio

        val size =
            Size(
                height,
                width,
            )

        fun pathStrings(color: Color) =
            listOf(
                Pair(
                    "M107.901 186.019C105.647 186.244 95.1738 181.245 93.1455 180.344C85.0322 177.189 63.774 164.145 31.7716 137.777C6.53032 116.818 -1.13225 87.5196 0.219985 67.0111C1.34683 49.883 11.9824 35.0157 23.2508 28.0293C17.842 36.1426 11.0378 46.2772 10.1363 57.0949C7.88259 83.4631 20.954 111.86 44.167 131.241C71.2113 153.553 105.97 171.555 121.521 180.344L107.901 186.019Z",
                    color.copy(0.7f),
                ),
                Pair(
                    "M183.911 108.156C159.571 143.314 134.104 168.555 124.188 178.02C123.061 179.147 121.033 179.373 119.681 178.697C107.511 172.386 75.5084 154.807 42.8299 127.538C21.1945 109.508 9.02455 83.3655 11.2782 59.251C12.8558 41.6722 21.6452 27.2486 36.0688 19.1353C37.4211 18.4592 38.5479 17.7831 39.9001 17.107C40.5762 16.6563 41.4777 16.4309 42.1538 15.9802C47.5626 13.7265 52.7461 12.8251 57.7042 12.8251C80.2411 12.8251 96.2423 31.756 98.7214 34.9112C99.1721 35.5873 99.6228 36.0381 100.524 36.4888C102.327 37.1649 104.13 36.2634 104.806 34.6858C105.482 32.8829 122.16 -7.68352 161.149 2.00734C177.375 6.06399 189.545 17.3324 195.855 34.0097C204.419 56.7719 199.687 85.1685 183.911 108.156Z",
                    color.copy(0.6f),
                ),
            )
    }

    val highlightColor = Color.Black

    private val colors =
        listOf(
            Color(0xFFD60270),
            Color(0xFF9B4F96),
            Color(0xFF0038A8),
            Color(0xFF55CDFC),
            Color(0xFFF7A8B8),
            Color(0xFF9C59D1),
            Color(0xFF000000),
            Color(0xFFD52D00),
            Color(0xFFEF7627),
            Color(0xFFFF9A56),
            Color(0xFFD162A4),
            Color(0xFFB55690),
            Color(0xFFA30262),
            Color(0xFFFF218C),
            Color(0xFF21B1FF),
            Color(0xFF078D70)
        )

    private val texts = listOf(
        "Enby\nLove",
        "Queer\nCutie",
        "U R\n Valid",
        "U\nBelong",
        "Ace\nPal",
        "Bi\nBestie",
        "Queer\njoy",
        "We're\nhere",
        "Eat the\nrich",
        "Queer &\nHere",
        "Love\nme",
        "Lick\nme",
        "They\nThem",
        "XO\nXO",
        "Love is\nLove",
        "Be\nMine",
        "Miss\nU",
    ).shuffled() // To get different combination on each render

    val hearts = colors.mapIndexed { index, color ->
        color to texts[index]
    }
}