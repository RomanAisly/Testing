package com.example.testing.domain.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testing.ui.screens.SectionsScreen
import com.example.testing.ui.screens.HomeScreen
import com.example.testing.ui.screens.SettingsScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NavScreens.HomeScreen.route
    ) {
        composable(route = NavScreens.HomeScreen.route) {
            HomeScreen {}
        }
        composable(route = NavScreens.SectionsScreen.route) {
            SectionsScreen {}
        }
        composable(route = NavScreens.SettingsScreen.route) {
            SettingsScreen {}
        }

    }
}

