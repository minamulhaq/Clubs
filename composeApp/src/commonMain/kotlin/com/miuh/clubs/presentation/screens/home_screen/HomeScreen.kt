package com.miuh.clubs.presentation.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import com.miuh.clubs.navigation.Routes
import com.miuh.clubs.presentation.ClubsViewModel
import com.miuh.clubs.presentation.screens.components.CcButton
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ClubsViewModel = koinViewModel(),
    navigateTo: (route: Routes) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var clubToTakeActionOn by remember { mutableStateOf<ClubDisplayListData?>(null) }

    val clubs = viewModel.clubs.collectAsStateWithLifecycle()

    var selectedButtonIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var currentGenType by rememberSaveable {
        mutableStateOf(GenType.GEN5)
    }

    var currentLeaderboardType by rememberSaveable {
        mutableStateOf(LeaderboardType.ALL_TIME)
    }

    val bookmarkedClubs by viewModel.bookmarkedClubs.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookmarkedClubsSection(clubs = bookmarkedClubs, onClubClicked = {

//            viewModel.onEvent(HomeScreenEvent.RemoveClubFromBookmarksClubEvent(it))
        })
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
                        currentGenType = button.genType
                        viewModel.onEvent(
                            HomeScreenEvent.GetTop100ClubsListEvent(
                                currentGenType, currentLeaderboardType
                            )
                        )
                    }, modifier = Modifier.weight(1f), colors = buttonColors
                ) {
                    Text(
                        text = button.title, fontSize = 10.sp
                    )
                }
            }
        }
        SearchFilterRow(clubSearchByName = {
            viewModel.onEvent(HomeScreenEvent.SearchClubByNameEvent(it))
        }, onLeaderBoardChanged = {
            currentLeaderboardType = it
            viewModel.onEvent(
                HomeScreenEvent.GetTop100ClubsListEvent(
                    currentGenType, currentLeaderboardType
                )
            )
        })

        ClubsDisplayListBlock(clubs = clubs.value, onClubClicked = {
            clubToTakeActionOn = it
            showBottomSheet = true
        })


        val scope = rememberCoroutineScope()
        if (showBottomSheet) {
            ModalBottomSheet(
                sheetState = bottomSheetState,
                onDismissRequest = {
                    scope.launch { bottomSheetState.hide() }
                    showBottomSheet = false
                    clubToTakeActionOn = null
                }
            ) {
                ClubActionModalBottomSheet(
                    club = clubToTakeActionOn!!,
                    okButton = {
                        CcButton(
                            buttonOnClick = {
                                viewModel.onEvent(HomeScreenEvent.AddClubToBookmarksClubEvent(it))
                                showBottomSheet = false
                                clubToTakeActionOn = null

                            },
                            buttonText = "Bookmark Club"
                        )
                    }
                )
            }

        }
    }
}

