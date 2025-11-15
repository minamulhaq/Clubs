package com.miuh.clubs.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import com.miuh.clubs.presentation.screens.home_screen.HomeScreenEvent
import com.miuh.clubs.ui.theme.inversePrimaryLight
import com.miuh.clubs.ui.theme.primaryLightMediumContrast
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun CcButtons(
    modifier: Modifier = Modifier.fillMaxWidth(),
    bookmarked: Boolean,
    buttonOnClick: (HomeScreenEvent) -> Unit,
    club: ClubDisplayListData
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CcButton(
            modifier = Modifier.weight(1f),
            buttonOnClick = {
                if (bookmarked)
                    buttonOnClick(HomeScreenEvent.RemoveClubFromBookmarksClubEvent(club))
                else
                    buttonOnClick(HomeScreenEvent.AddClubToBookmarksClubEvent(club))
            },
            buttonText = if (!bookmarked) "Bookmark" else "Remove Bookmark",
            bookmarked = false
        )
        Spacer(Modifier.width(8.dp))
        CcButton(
            modifier = Modifier.weight(1f),
            buttonOnClick = {
                buttonOnClick(HomeScreenEvent.ToClubDetailsScreenClubEvent(club))
            },
            buttonText = "Details",
            bookmarked = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CcButtonsPreview() {
    val club: ClubDisplayListData = ClubDisplayListData(
        clubId = "2009",
        clubName = "Gulagis",
        skillRating = "2004",
        crestImageUrl = "https://eafc24.content.easports.com/fifa/fltOnlineAssets/24B23FDE-7835-41C2-87A2-F453DFDB2E82/2024/fcweb/crests/256x256/l99161123.png"
    )

    Column {
        CcButtons(
            bookmarked = true, buttonOnClick = {},
            club = club
        )
        CcButtons(
            bookmarked = false, buttonOnClick = {},
            club = club
        )

    }
}

