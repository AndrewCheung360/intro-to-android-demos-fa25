package com.example.demo3_starter.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.demo3_starter.ui.navigation.Screen.EntryScreen.toScreen
import com.example.demo3_starter.ui.screens.EntryScreen
import com.example.demo3_starter.ui.screens.MoodScreen
import com.example.demo3_starter.ui.screens.WelcomeScreen

@Composable
fun DemoNavigation() {
    //TODO 1: Create a navController
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBar {
            tabs.map { item ->
                NavigationBarItem(
                    selected = item.screen == navBackStackEntry?.toScreen(),
                    onClick = {
                        //TODO 2: Navigate to the selected screen with navController
                        navController.navigate(item.screen)
                    },
                    icon = { Icon(imageVector = item.icon, contentDescription = null) },
                    label = { Text(text = item.label) }
                )
            }
        }
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = Screen.EntryScreen
            ) {
                composable<Screen.EntryScreen> {
                    EntryScreen(toWelcomeScreen = {
                        //TODO 3: Navigate to WelcomeScreen with navController and pass name as argument
                        navController.navigate(Screen.WelcomeScreen(it))
                    })
                }
                composable<Screen.WelcomeScreen> { navBackStackEntry ->
                    val name = navBackStackEntry.toRoute<Screen.WelcomeScreen>().name
                    WelcomeScreen(
                        name,
                        toMoodScreen = { navController.navigate(Screen.MoodScreen) })
                }
                //TODO 4: Add MoodScreen composable destination
                composable<Screen.MoodScreen> {
                    MoodScreen()
                }
            }
        }
    }
}

data class NavItem(
    val screen: Screen,
    val label: String,
    val icon: ImageVector
)

val tabs = listOf(
    NavItem(
        label = "Home",
        icon = Icons.Filled.Home,
        screen = Screen.EntryScreen,
    ),
    NavItem(
        label = "Profile",
        icon = Icons.Filled.Person,
        screen = Screen.MoodScreen,
    )
)