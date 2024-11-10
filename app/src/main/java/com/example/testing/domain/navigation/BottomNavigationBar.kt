package com.example.testing.domain.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {
    val bottomScreens = remember {
        listOf(
            BottomScreens.Home,
            BottomScreens.Profile,
            BottomScreens.Settings
        )
    }

    BottomAppBar(contentPadding = BottomAppBarDefaults.ContentPadding) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomScreens.forEach { screen ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.route == screen.route::class.qualifiedName } == true

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }, icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = screen.icon),
                        contentDescription = ""
                    )

                }, label = {
                    Text(
                        text = LocalContext.current.getString(screen.label),
                        color = if (isSelected) Color.Blue else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}