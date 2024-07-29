package com.example.testing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testing.R
import com.example.testing.ui.theme.backForSectionsScreen

@Composable
fun DetailsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backForSectionsScreen),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.map), contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier
                .wrapContentSize()
                .size(370.dp)
        )
        LazyColumn(
            modifier = Modifier
                .wrapContentSize(Alignment.BottomCenter),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(8) {
                Sections()
            }
        }
    }
}

@Composable
fun Sections() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.tennis), contentDescription = "",
            modifier = Modifier
                .wrapContentSize()
                .size(55.dp)
        )
        Text(
            text = "Monkey Fly ",
            fontSize = 14.sp,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.wrapContentSize()
        )
        Button(
            onClick = {},
            modifier = Modifier.wrapContentSize(),
            colors = ButtonColors(
                containerColor = Color.Red,
                contentColor = Color.White,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Cyan
            )
        ) {
            Text(
                text = "Go to...",
                fontSize = 14.sp,
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    DetailsScreen()
}