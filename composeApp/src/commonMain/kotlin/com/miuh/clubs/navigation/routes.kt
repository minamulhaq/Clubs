package com.miuh.clubs.navigation

import kotlinx.serialization.Serializable

sealed interface Routes


@Serializable
object HomeRoute : Routes

@Serializable
object SettingsRoute : Routes
