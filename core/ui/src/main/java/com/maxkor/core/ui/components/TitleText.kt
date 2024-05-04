package com.maxkor.core.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.maxkor.core.theme.LocalFontScaling

@Composable
fun TitleText(text: String) {
    val fontScaling = LocalFontScaling.current
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = fontScaling.increasingLarge,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif
    )
}