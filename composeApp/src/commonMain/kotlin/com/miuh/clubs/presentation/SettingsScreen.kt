package com.miuh.clubs.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Settings Screen")
    }
}