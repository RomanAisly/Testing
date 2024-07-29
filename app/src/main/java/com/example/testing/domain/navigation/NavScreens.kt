package com.example.testing.domain.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sports
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.testing.R

sealed class NavScreens(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    data object HomeScreen : NavScreens(R.string.bottom_nav_item_home, Icons.Default.Home, "home")
    data object SectionsScreen :
        NavScreens(R.string.bottom_nav_item_sections, Icons.Default.Sports, "sections")

    data object SettingsScreen :
        NavScreens(R.string.bottom_nav_item_settings, Icons.Default.Settings, "settings")

    data object DetailsScreen :
        NavScreens(R.string.bottom_nav_item_details, Icons.Default.Details, "details")
}