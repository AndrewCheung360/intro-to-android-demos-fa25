package com.example.demo3_starter.ui.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object EntryScreen : Screen()

    @Serializable
    data class WelcomeScreen(val name: String) : Screen()

    @Serializable
    data object MoodScreen : Screen()

    fun NavBackStackEntry.toScreen(): Screen? =
        when (destination.route?.substringAfterLast(".")?.substringBefore("/")) {
            "HomeScreen" -> toRoute<EntryScreen>()
            "WelcomeScreen" -> toRoute<WelcomeScreen>()
            "MoodScreen" -> toRoute<MoodScreen>()
            else -> null
        }
}