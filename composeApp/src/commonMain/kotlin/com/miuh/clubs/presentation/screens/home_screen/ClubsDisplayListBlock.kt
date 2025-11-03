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


@Composable
fun StaticLeaderboardTableHeader() {
    val headerTitles = listOf(
        "Ranks", "Crest", "Club Name", "Skill Rating",
        "Reputation", "Highest Division", "Matches Played"
    )

    // Outer Row for the entire header with a light background to provide context
    Row(
        modifier = Modifier
            .fillMaxWidth()
//            .background(Color(0xFFE0E0E0)) // Light gray background
            .padding(vertical = 4.dp)
    ) {
        // Inner Row for the green columns
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background) // Bright Green background
        ) {
            headerTitles.forEachIndexed { index, title ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        // Adjust weight for "Club Name" (index 2) to be wider, as often seen in lists
                        .background(MaterialTheme.colorScheme.onPrimaryContainer)
                        // Apply white border to the left side of every column except the first
                        // This creates the visual effect of white vertical lines between columns.
                        .border(
                            width = 1.dp,
                            color = if (index > 0) Color.White else Color.Transparent,
                            shape = RectangleShape
                        )
                        .padding(horizontal = 4.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun ClubsDisplayListBlock(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StaticLeaderboardTableHeader()

    }
}