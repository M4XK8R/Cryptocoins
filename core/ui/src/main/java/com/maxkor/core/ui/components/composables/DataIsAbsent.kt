package com.maxkor.core.ui.components.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maxkor.core.ui.components.view.TitleText

@Composable
fun DataIsAbsent(
    text: String,
    modifier: Modifier = Modifier,
) = Box(
    modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .then(modifier), contentAlignment = Alignment.Center
) {
    TitleText(text = text)
}
