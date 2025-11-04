package com.miuh.clubs.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.compose.LocalPlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import org.koin.compose.koinInject


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    val imageLoader = koinInject<ImageLoader>()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Button(onClick = {
                imageLoader.diskCache?.clear()
                imageLoader.memoryCache?.clear()
            }) {
                Text(text = "Clear Cache")
            }
        }
    }
}