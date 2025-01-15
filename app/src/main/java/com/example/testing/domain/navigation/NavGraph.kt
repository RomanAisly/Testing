package com.example.testing.domain.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testing.ui.screens.HomeScreen
import com.example.testing.ui.screens.Profile
import com.example.testing.ui.screens.Settings

@Composable
fun NavGraph(modifier: Modifier = Modifier, navHostController: NavHostController) {
    Scaffold(containerColor = MaterialTheme.colorScheme.background,
        bottomBar = { BottomNavBar(navController = navHostController) }

    ) { innerPadding ->
        NavHost(
            modifier = modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            navController = navHostController,
            startDestination = Screens.SettingsScreen,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 900 },
                    animationSpec = tween(
                        durationMillis = 1200,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 900 },
                    animationSpec = tween(
                        durationMillis = 1200,
                        easing = FastOutSlowInEasing
                    )
                )
            }

        ) {
            composable<Screens.HomeScreen> {
                HomeScreen()
            }
            composable<Screens.ProfileScreen> {
                Profile()
            }
            composable<Screens.SettingsScreen> {
                Settings()
            }
        }
    }
}