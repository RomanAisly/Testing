package com.example.testing.domain.navigation

import androidx.annotation.StringRes
import com.example.testing.R
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomScreens<T>(@StringRes val label: Int, val icon: Int, val route: T) {
    @Serializable
    data object Home : BottomScreens<HomeNavScreen>(
        label = R.string.nav_item_home,
        icon = R.drawable.ic_home,
        route = HomeNavScreen
    )

    @Serializable
    data object Profile : BottomScreens<ProfileNavScreen>(
        label = R.string.nav_item_favourites,
        icon = R.drawable.ic_profile,
        route = ProfileNavScreen
    )

    @Serializable
    data object Settings : BottomScreens<SettingsNavScreen>(
            label = R.string.nav_item_responses,
            icon = R.drawable.ic_settings,
            route = SettingsNavScreen
        )
}