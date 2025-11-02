package com.miuh.clubs.presentation.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miuh.clubs.navigation.Routes
import com.miuh.clubs.presentation.ClubsViewModel
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.foundation.lazy.items


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ClubsViewModel = koinViewModel(),
    navigateTo: (route: Routes) -> Unit
) {
    val clubs = viewModel.clubs.collectAsStateWithLifecycle()
    var selectedButtonIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bookmarked Clubs")
        Text(text = "Top 100 Ratings")
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            genFilterButtons.forEachIndexed { index, button ->
                val isSelected = index == selectedButtonIndex

                val buttonColors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                )

                Button(
                    onClick = {
                        selectedButtonIndex = index
                        viewModel.onEvent(HomeScreenEvent.GetClubsListEvent(button.filter))
                    }, modifier = Modifier.weight(1f), colors = buttonColors
                ) {
                    Text(
                        text = button.title, fontSize = 10.sp
                    )
                }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f) // Use weight to fill remaining space
        ) {
            // Renders an item for each Club object in the 'clubs.value' list
            items(items = clubs.value, key = { it.clubId }) { club ->
                // Print the clubName string for each item
                Text(
                    text = club.clubName,
                    // Optionally add padding or other modifiers
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // Optional: Show a loading/empty state if the list is empty
            if (clubs.value.isEmpty()) {
                item {
                    // Check for an actual loading state or show a message
                    Text(
                        text = "No clubs found or loading...",
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    )
                }
            }
        }

    }
}
