package com.example.testing.domain.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testing.ui.screens.LoginScreen
import com.example.testing.ui.screens.MainScreen
import com.example.testing.ui.screens.RegisterScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NavScreens.LoginScreen
    ) {
        composable<NavScreens.LoginScreen> {
            LoginScreen {
                navHostController.navigate(it)
            }
        }
        composable<NavScreens.RegisterScreen> {
            RegisterScreen {
                navHostController.navigate(it)
            }
        }
        composable<NavScreens.MainScreen> {
            MainScreen {
                navHostController.navigate(it)
            }
        }
    }
}

