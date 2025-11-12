package com.miuh.clubs.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.miuh.clubs.ui.theme.inversePrimaryLight
import com.miuh.clubs.ui.theme.primaryLightMediumContrast
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CcButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    buttonOnClick: () -> Unit,
    bookmarked: Boolean
) {
    Button(
        modifier = modifier, onClick = buttonOnClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (bookmarked) {
                primaryLightMediumContrast
            } else {
                inversePrimaryLight
            }
        )
    ) {
        Text(
            text = if (bookmarked) {
                "Remove Club"
            } else {
                "Bookmark Club"
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CcButtonPreview() {
    Column {
        CcButton(bookmarked = false, buttonOnClick = {})
        CcButton(bookmarked = true, buttonOnClick = {})

    }
}

