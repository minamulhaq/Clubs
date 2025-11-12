package com.miuh.clubs.presentation.screens.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.BeyondBoundsLayout
import com.miuh.clubs.core.data.db.local.ClubEntity
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import com.miuh.clubs.presentation.screens.components.CcButton
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkedClubsSection(
    modifier: Modifier = Modifier,
    clubs: List<ClubDisplayListData>,
    onClubClicked: (ClubDisplayListData) -> Unit,
    isBookmarked: (ClubDisplayListData) -> Boolean

) {
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    var clubToTakeActionOn by remember { mutableStateOf<ClubDisplayListData?>(null) }

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bookmarked Clubs")
        if (clubs.isEmpty()) {
            Text(text = "No Bookmarked Clubs Found")
        }
        clubs.forEach { club ->
            SingleClubDisplayRow(club = club, onClubClicked = {
                clubToTakeActionOn = club
                showBottomSheet = true
            })
        }
        val scope = rememberCoroutineScope()

        if (showBottomSheet) {
            ModalBottomSheet(
                sheetState = bottomSheetState,
                onDismissRequest = {
                    scope.launch {
                        showBottomSheet = false
                        bottomSheetState.hide()
                    }
                }
            ) {

                ClubActionModalBottomSheet(
                    club = clubToTakeActionOn!!,
                    okButton = {
                        CcButton(
                            buttonOnClick = {
                            },
                            bookmarked = isBookmarked(clubToTakeActionOn!!)
                        )
                    }
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarkedClubsSectionPreview() {
    BookmarkedClubsSection(
        clubs = emptyList(), onClubClicked = {}, isBookmarked = {
            true
        }
    )
}
