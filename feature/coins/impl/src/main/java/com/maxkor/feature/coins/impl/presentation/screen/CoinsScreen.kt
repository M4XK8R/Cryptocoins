package com.maxkor.feature.coins.impl.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.maxkor.core.theme.CryptocoinsTheme
import com.maxkor.core.theme.LocalSpacing
import com.maxkor.core.ui.components.ButtonText
import com.maxkor.core.ui.components.TitleText
import com.maxkor.core.ui.composables.Loading
import com.maxkor.feature.coins.impl.presentation.viewmodel.CoinsViewModel

@Composable
fun CoinsScreen(
    coinsUiState: CoinsUiState,
    onBackClick: () -> Unit,
    navigateToDetail: () -> Unit,
    viewModel: CoinsViewModel,
    modifier: Modifier = Modifier,
) {
    when (coinsUiState) {
        is CoinsUiState.Loading -> Loading()
        is CoinsUiState.Success -> Content(
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
fun Content(
    navigateToDetail: () -> Unit,
) {
    val spacing = LocalSpacing.current
    CryptocoinsTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TitleText(text = "Coins screen")
                Spacer(modifier = Modifier.size(spacing.spaceMedium))
                Button(
                    onClick = navigateToDetail
                ) {
                    ButtonText(text = "Navigate to detail")
                }
            }
        }
    }
}

