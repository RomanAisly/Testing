package com.example.testing.domain.navigation

import androidx.annotation.StringRes
import com.example.testing.R
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomScreens<T>(@StringRes val label: Int, val icon: Int, val route: T) {
    @Serializable
    data object Home : BottomScreens<Screens.HomeScreen>(
        label = R.string.nav_item_home,
        icon = R.drawable.ic_home,
        route = Screens.HomeScreen
    )

    @Serializable
    data object Profile : BottomScreens<Screens.ProfileScreen>(
        label = R.string.nav_item_favourites,
        icon = R.drawable.ic_profile,
        route = Screens.ProfileScreen
    )

    @Serializable
    data object Settings : BottomScreens<Screens.SettingsScreen>(
            label = R.string.nav_item_responses,
            icon = R.drawable.ic_settings,
            route = Screens.SettingsScreen
        )
}