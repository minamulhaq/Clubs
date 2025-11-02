package com.miuh.clubs

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miuh.clubs.navigation.HomeRoute
import com.miuh.clubs.navigation.SettingsRoute
import com.miuh.clubs.presentation.HomeScreen
import com.miuh.clubs.ui.theme.ClubsTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    ClubsTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = HomeRoute) {
            composable<HomeRoute> {
                HomeScreen(navigateTo = {
                    navController.navigate(it)
                })
            }
            composable<SettingsRoute> {
                Column {
                    Text(text = "Settings Route")
                }
            }
        }
    }
}