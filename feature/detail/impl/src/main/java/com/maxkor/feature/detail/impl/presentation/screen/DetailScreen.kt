package com.maxkor.feature.detail.impl.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maxkor.core.theme.CryptocoinsTheme
import com.maxkor.core.ui.components.TitleText
import com.maxkor.feature.detail.impl.presentation.viewmodel.DetailViewModel

@Composable
internal fun DetailScreen(
    onBackClick: () -> Unit,
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier,
) {
    CryptocoinsTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            TitleText(text = "Detail screen")
        }
    }
}