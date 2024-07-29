package com.example.testing.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testing.R
import com.example.testing.domain.navigation.NavScreens
import com.example.testing.ui.theme.backForSectionsScreen

@Composable
fun SectionsScreen(navigateTo: (NavScreens) -> Unit) {

    val items = listOf(
        SectionItem(
            painterResource(id = R.drawable.tennis),
            "Tennis"
        ),
        SectionItem(
            painterResource(id = R.drawable.hockey),
            "Hockey"
        ),
        SectionItem(
            painterResource(id = R.drawable.swimming),
            "Swimming"
        ),
        SectionItem(
            painterResource(id = R.drawable.judo),
            "Judo"
        ),
        SectionItem(
            painterResource(id = R.drawable.football),
            "Football"
        ),
        SectionItem(
            painterResource(id = R.drawable.athletic),
            "Athletic"
        ),
        SectionItem(
            painterResource(id = R.drawable.gymnastics),
            "Gymnastics"
        ),
        SectionItem(
            painterResource(id = R.drawable.wrestling),
            "Wrestling"
        ),
    )
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(backForSectionsScreen),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalArrangement = Arrangement.SpaceAround,
        columns = GridCells.Fixed(2),
        userScrollEnabled = true,
        flingBehavior = ScrollableDefaults.flingBehavior()
    ) {
        items(items) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = it.icon, contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .size(130.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = it.title,
                    modifier = Modifier
                        .fillMaxSize(),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

data class SectionItem(
    val icon: Painter,
    val title: String
)

@Preview
@Composable
private fun Preview() {
    SectionsScreen {

    }
}