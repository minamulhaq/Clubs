package com.miuh.clubs.presentation.screens.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import com.miuh.clubs.core.data.schema.ClubInfo
import com.miuh.clubs.core.data.schema.CustomKit
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun SingleClubDisplayRow(
    modifier: Modifier = Modifier,
    club: ClubDisplayListData,
    onClubClicked: (ClubDisplayListData) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClubClicked(club) }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = club.crestImageUrl,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(Modifier.width(12.dp))
            Text(text = club.clubName, style = MaterialTheme.typography.bodyLarge)
        }
        Text(
            text = club.skillRating,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Composable
@Preview(showBackground = true)
fun SingleClubDisplayRowPreview() {
    val customKitData = CustomKit(
        crestAssetId = "99161123",
        crestColor = "-1",
        customAwayKitId = "7522",
        customKeeperKitId = "5005",
        customKitId = "7730",
        customThirdKitId = "7637",
        dCustomKit = "0",
        kitAColor1 = "5775459",
        kitAColor2 = "15921906",
        kitAColor3 = "15921906",
        kitAColor4 = "15921906",
        kitColor1 = "16774144",
        kitColor2 = "12706617",
        kitColor3 = "16774144",
        kitColor4 = "8654629",
        kitId = "40960",
        kitThrdColor1 = "14358546",
        kitThrdColor2 = "12525622",
        kitThrdColor3 = "14358546",
        kitThrdColor4 = "16774144",
        seasonalKitId = "1076461568",
        seasonalTeamId = "131404",
        selectedKitType = "1",
        stadName = "Tier 1 Stadium"
    )

    val clubInfoData = ClubInfo(
        clubId = 490431, customKit = customKitData, name = "gulagis", regionId = 5064001, teamId = 5
    )

    val clubs = listOf<ClubDisplayListData>(
        ClubDisplayListData(
            clubId = "490431", clubName = clubInfoData.name, clubInfo = clubInfoData
        )
    )

    SingleClubDisplayRow(club = clubs[0], onClubClicked = {})
}
