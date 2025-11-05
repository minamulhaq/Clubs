package com.miuh.clubs.presentation.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ClubActionModalBottomSheet(
    modifier: Modifier = Modifier,
    club: ClubDisplayListData,
    okButton: @Composable (ClubDisplayListData) -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            club
            SubcomposeAsyncImage(
                model = club.crestImageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                error = {
                    if (LocalInspectionMode.current) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                })
            Text(club.clubName, style = MaterialTheme.typography.titleLarge)
        }
        Spacer(Modifier.height(8.dp))
        okButton(club)
//        Button(onClick = { bookmarkClub(club) }) {
//            Text("Bookmark club")
//        }

    }
}

@Preview(showBackground = true)
@Composable
fun ClubActionModalBottomSheetPreview(
) {

    val club: ClubDisplayListData = ClubDisplayListData(
        clubId = "2009",
        clubName = "Gulagis",
        skillRating = "2004",
        crestImageUrl = "https://eafc24.content.easports.com/fifa/fltOnlineAssets/24B23FDE-7835-41C2-87A2-F453DFDB2E82/2024/fcweb/crests/256x256/l99161123.png"
    )
    ClubActionModalBottomSheet(club = club , okButton = {})
}
