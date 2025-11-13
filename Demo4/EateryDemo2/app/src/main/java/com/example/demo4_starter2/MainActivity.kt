package com.example.demo4_starter2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demo4_starter2.ui.screens.EateryScreen
import com.example.demo4_starter2.ui.theme.Demo4_starter2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demo4_starter2Theme {
                val navController = rememberNavController()

                Scaffold() { innerPadding ->
                    Box(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
                        NavHost(
                            navController = navController,
                            startDestination = "EateryScreen"
                        ) {
                            composable("EateryScreen") {
                                EateryScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}