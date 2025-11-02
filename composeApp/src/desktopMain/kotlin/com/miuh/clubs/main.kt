package com.miuh.clubs

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.miuh.clubs.core.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KoinAnnotationsGuide",
        ) {
            App()
        }
    }
}