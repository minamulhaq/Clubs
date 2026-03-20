package com.miuh.clubs.presentation.screens.home_screen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miuh.clubs.core.data.LeaderboardType
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun SearchFilterRow(
    modifier: Modifier = Modifier,
    clubSearchByName: (String) -> Unit,
    onLeaderBoardChanged: (LeaderboardType) -> Unit
) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(LeaderboardType.ALL_TIME.ordinal) }
    var searchText by rememberSaveable { mutableStateOf("") }

    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier.height(84.dp)
    ) {
        LeaderboardType.entries.forEachIndexed { index, destination ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onLeaderBoardChanged(destination)
                },
                text = {
                    Text(
                        text = destination.displayString,
                    )
                }
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .border(border = BorderStroke(width = 0.dp, MaterialTheme.colorScheme.surfaceContainerLow))
                .onPreviewKeyEvent {
                    println("KEY PRESSED ${it.key}")
                    true
                },
            value = searchText.trim(),
            onValueChange = {
                searchText = it
            },
            placeholder = { Text(text = "Search club...", fontSize = 10.sp) },
            trailingIcon = {
                Row(
                    modifier = Modifier
                        .padding(end = 10.dp)
                ) {
                    if (searchText != "") {
                        Icon(
                            modifier = Modifier
                                .clickable(onClick = {
                                    searchText = ""
                                }),
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Search"
                        )

                        Spacer(modifier = Modifier.width(5.dp))
                    }
                    Icon(
                        modifier = Modifier
                            .clickable(onClick = {
                                clubSearchByName(searchText)
                            }),
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Club"
                    )

                }
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant
            ),
        )

    }
}


@Preview(showBackground = true)
@Composable
private fun TabNavigationPreview() {
    SearchFilterRow(clubSearchByName = {}, onLeaderBoardChanged = {})
}
