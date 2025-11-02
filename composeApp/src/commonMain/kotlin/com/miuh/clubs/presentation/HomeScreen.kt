package com.miuh.clubs.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miuh.clubs.navigation.Routes
import com.miuh.clubs.navigation.SettingsRoute
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ClubsViewModel = koinViewModel(),
    navigateTo: (route: Routes) -> Unit
) {

    val clubs = viewModel.state.collectAsStateWithLifecycle()
    Column {
        Text(text = "HomeRoute")
        Button(onClick = {
            navigateTo(SettingsRoute)
        }) {
            Text("Click")
        }
    }

}