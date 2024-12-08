package com.example.testing.domain.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    object HomeScreen : Screens()

    @Serializable
    object ProfileScreen : Screens()

    @Serializable
    object SettingsScreen : Screens()
}
