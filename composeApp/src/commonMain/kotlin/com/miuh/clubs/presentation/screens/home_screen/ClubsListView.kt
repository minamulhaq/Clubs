package com.miuh.clubs.presentation.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import co.touchlab.kermit.Logger
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import com.miuh.clubs.presentation.screens.components.CcButtonsEvent
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubsListView(
    modifier: Modifier = Modifier,
    title: String,
    emptyMessage: String = "No clubs found",
    clubs: List<ClubDisplayListData>,
    isBookmarked: (ClubDisplayListData) -> Boolean,
    onButtonClick: (HomeScreenEvent) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var clubToTakeActionOn by remember { mutableStateOf<ClubDisplayListData?>(null) }

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        if (clubs.isEmpty()) {
            Text(text = emptyMessage)
        }
        LazyColumn {
            items(clubs) { club ->
                SingleClubDisplayRow(club = club, onClubClicked = {
                    clubToTakeActionOn = club
                    showBottomSheet = true
                })

            }
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
                    bookmarked = isBookmarked(clubToTakeActionOn!!),
                    onButtonClick = { event ->
                        when (event) {
                            is HomeScreenEvent.ToClubDetailsScreenClubEvent -> showBottomSheet =
                                false

                            else -> Unit
                        }
                        onButtonClick(event)
                    }
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarkedClubsSectionPreview() {
    ClubsListView(
        title = "Bookmarked Clubs",
        emptyMessage = "No Bookmarked Clubs Found",
        clubs = emptyList(),
        isBookmarked = { true },
        onButtonClick = {}
    )
}
