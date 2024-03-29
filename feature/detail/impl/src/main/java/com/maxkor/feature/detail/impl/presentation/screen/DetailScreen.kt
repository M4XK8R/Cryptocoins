package com.maxkor.feature.detail.impl.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkor.core.ui.components.TitleText

@Composable
internal fun DetailScreen(
    name: String,
    price: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .then(modifier),
        verticalArrangement = Arrangement.Center
    ) {
        TitleText(text = name)
        TitleText(text = price)
        TitleText(text = imageUrl)
    }
}