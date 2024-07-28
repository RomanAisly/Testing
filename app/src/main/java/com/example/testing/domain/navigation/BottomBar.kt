package com.example.testing.domain.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomBar(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val screens = listOf(
        NavScreens.HomeScreen,
        NavScreens.SectionsScreen,
        NavScreens.SettingsScreen
    )
    val navBackStack by navHostController.currentBackStackEntryAsState()
    val currentScreen = navBackStack?.destination?.route

    BottomAppBar {
        screens.forEach { screenItem ->
            NavigationBarItem(
                selected = currentScreen == screenItem.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = LocalContentColor.current.copy(alpha = 0.5f),
                    selectedTextColor = Color.White,
                    unselectedTextColor = LocalContentColor.current.copy(alpha = 0.5f)
                ),
                onClick = {
                    navHostController.navigate(screenItem.route) {
                        popUpTo(navHostController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screenItem.icon, contentDescription = ""
                    )
                },
                label = {
                    Text(
                        text = LocalContext.current.getString(screenItem.title),
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                })
        }
    }
}