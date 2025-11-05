package com.miuh.clubs.presentation.screens.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miuh.clubs.core.data.db.local.ClubEntity
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun BookmarkedClubsSection(
    modifier: Modifier = Modifier,
    clubs: List<ClubDisplayListData>,
    onClubClicked: (ClubDisplayListData) -> Unit

) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bookmarked Clubs")
        if(clubs.isEmpty()){
            Text(text = "No Bookmarked Clubs Found")
        }
        clubs.forEach { club ->
            SingleClubDisplayRow(club = club, onClubClicked = onClubClicked)
        }
    }
}

@Preview
@Composable
fun BookmarkedClubsSectionPreview() {
    BookmarkedClubsSection(
        clubs = emptyList(), onClubClicked = {}
    )
}
