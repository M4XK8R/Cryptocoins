package com.maxkor.core.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maxkor.core.ui.components.TitleText

@Composable
fun DataIsAbsent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TitleText(text = "No valid data is available")
    }
}