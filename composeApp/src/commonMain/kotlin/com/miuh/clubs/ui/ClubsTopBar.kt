package com.miuh.clubs.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubsTopBar(
    modifier: Modifier = Modifier, onOpenDrawer: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
    ),

        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 16.dp, end = 8.dp).clickable {
                    onOpenDrawer()
                }, imageVector = Icons.Default.Menu, contentDescription = "Menu Icon"
            )
        }, title = {}, actions = {

            Icon(
                modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                imageVector = Icons.Default.Notifications,
                contentDescription = "Icon Notifications"
            )
            Icon(
                modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account"
            )

        }

    )
}