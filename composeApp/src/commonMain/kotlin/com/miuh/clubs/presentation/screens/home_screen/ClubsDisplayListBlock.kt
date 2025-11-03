package com.miuh.clubs.presentation.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun StaticLeaderboardTableHeader(
    modifier: Modifier = Modifier
) {
    val headerTitles = listOf(
        "Ranks",
        "Crest",
        "Club Name",
        "Skill Rating",
        "Reputation",
        "Highest Division",
        "Matches Played"
    )

    Row(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
    ) {
        headerTitles.forEachIndexed { index, title ->
            Box(
                modifier = Modifier.weight(1f)
                    .background(MaterialTheme.colorScheme.onPrimaryContainer).border(
                        width = 1.dp,
                        color = if (index > 0) Color.White else Color.Transparent,
                        shape = RectangleShape
                    ).padding(horizontal = 4.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}

@Preview
@Composable
fun ClubsDisplayListBlock(
    modifier: Modifier = Modifier, clubs: List<ClubDisplayListData>
) {
    StaticLeaderboardTableHeader(modifier = modifier)
    LazyColumn {
        clubs
        items(clubs) { club ->
            SingleClubDisplayRow(club = club, onClubClicked = {
                println("Clicked club: ${club.clubName}")

            })
            Spacer(modifier = modifier.height(10.dp))
        }
    }
}