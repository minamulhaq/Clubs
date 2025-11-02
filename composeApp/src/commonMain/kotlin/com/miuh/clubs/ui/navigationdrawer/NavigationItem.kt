package com.miuh.clubs.ui.navigationdrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.miuh.clubs.navigation.HomeRoute
import com.miuh.clubs.navigation.Routes
import com.miuh.clubs.navigation.SettingsRoute

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val navigationRoute: Routes? = null,
)


val navigationItems = listOf<NavigationItem>(
    NavigationItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        navigationRoute = HomeRoute
    ),
    NavigationItem(
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        navigationRoute = SettingsRoute
    ),
)