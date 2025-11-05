package com.miuh.clubs.presentation.screens.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CcButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    buttonOnClick: () -> Unit
) {
    Button(modifier = modifier, onClick = buttonOnClick) {
        Text(text = buttonText)
    }
}

@Preview(showBackground = true)
@Composable
fun CcButtonPreview() {
    CcButton(buttonText = "Bookmark", buttonOnClick = {})
}

