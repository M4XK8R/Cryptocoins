package com.maxkor.feature.detail.impl.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun CoinNameText(
    text: String,
    fontScaling: TextUnit,
) = Text(
    text = text,
    color = MaterialTheme.colorScheme.onBackground,
    fontSize = fontScaling,
    style = MaterialTheme.typography.titleLarge,
    fontWeight = FontWeight.Bold,
    fontFamily = FontFamily.Serif,
)