package com.example.testing.domain.navigation

import kotlinx.serialization.Serializable

sealed class NavScreens {
    @Serializable
    data object LoginScreen : NavScreens()

    @Serializable
    data object RegisterScreen : NavScreens()

    @Serializable
    data object MainScreen : NavScreens()
}