package com.miuh.clubs.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.miuh.clubs.ui.theme.inversePrimaryDark
import com.miuh.clubs.ui.theme.inversePrimaryLight
import com.miuh.clubs.ui.theme.primaryLightMediumContrast
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
        colors =
            ButtonDefaults.buttonColors(
                when (bookmarked) {
                    true -> primaryLightMediumContrast
                    else -> inversePrimaryLight
                }
            )
    ) {
        Text(
            text = buttonText,
            color = when (bookmarked) {
                true -> Color.White
                else -> Color.Black
            }
        )
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

