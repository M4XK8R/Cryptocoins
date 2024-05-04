package com.maxkor.core.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.maxkor.core.theme.LocalFontScaling

@Composable
fun ButtonText(text: String) {
    val fontScaling = LocalFontScaling.current
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontSize = fontScaling.increasingExtraSmall,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Serif
    )
}