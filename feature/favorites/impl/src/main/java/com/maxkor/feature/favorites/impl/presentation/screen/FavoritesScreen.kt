package com.maxkor.feature.favorites.impl.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maxkor.core.theme.CryptocoinsTheme
import com.maxkor.feature.favorites.impl.presentation.viewmodel.FavoritesViewModel

@Composable
internal fun FavoritesScreen(
    onBackClick: () -> Unit,
    viewModel: FavoritesViewModel,
    modifier: Modifier = Modifier,
) {
    CryptocoinsTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "FAVORITES SCREEN",
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}