package com.miuh.clubs.presentation.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miuh.clubs.core.data.schema.ClubDisplayListData

@Composable
fun ClubActionModalBottomSheet(
    modifier: Modifier = Modifier,
    club: ClubDisplayListData,
    bookmarkClub: (ClubDisplayListData) -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Club Details")
        Text("Club Name: ${club.clubName}", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { bookmarkClub(club) }) {
            Text("Bookmark club")
        }
    }
}
