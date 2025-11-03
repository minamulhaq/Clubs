package com.miuh.clubs.presentation.screens.home_screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class TimeFilterTab {
    ALL_TIME, CURRENT_SEASON
}


@Composable
fun SearchFilterRow(
    modifier: Modifier = Modifier,
    clubSearchByName: (String) -> Unit
) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(TimeFilterTab.ALL_TIME.ordinal) }
    val searchText = mutableStateOf("")

    PrimaryTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier.height(84.dp)
    ) {
        TimeFilterTab.entries.forEachIndexed { index, destination ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                },
                text = {
                    Text(
                        text = destination.name,
                    )
                }
            )
        }


        OutlinedTextField(
            modifier = Modifier,
            value = searchText.value,
            onValueChange = {
                searchText.value = it
            },
            placeholder = { Text(text = "Search club...", fontSize = 10.sp) },
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .clickable(onClick = {
                            clubSearchByName(searchText.value)
                        }),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Club"
                )
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
    SearchFilterRow(clubSearchByName = {})
}
