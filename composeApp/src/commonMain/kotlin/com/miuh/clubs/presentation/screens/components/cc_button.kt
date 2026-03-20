package com.miuh.clubs.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun CcButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    buttonOnClick: () -> Unit,
    bookmarked: Boolean? = null,
    buttonText: String
) {
    Button(
        modifier = modifier, onClick = buttonOnClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (bookmarked == true) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer,
            contentColor = if (bookmarked == true) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Text(text = buttonText)
    }
}


@Preview(showBackground = true)
@Composable
fun CcButtonPreview() {
    Column {
        CcButton(bookmarked = false, buttonOnClick = {}, buttonText = "Details")
        CcButton(bookmarked = true, buttonOnClick = {}, buttonText = "Bookmark")

    }
}

