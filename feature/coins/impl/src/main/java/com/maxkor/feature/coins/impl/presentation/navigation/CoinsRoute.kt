package com.maxkor.feature.coins.impl.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxkor.feature.coins.impl.presentation.screen.CoinsScreen
import com.maxkor.feature.coins.impl.presentation.viewmodel.CoinsViewModel

@Composable
fun CoinsRoute(
    onBackClick: () -> Unit,
    navigateToDetail: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: CoinsViewModel = hiltViewModel()
    val coinsUiState by viewModel.coinsUiState.collectAsStateWithLifecycle()

    CoinsScreen(
        viewModel = viewModel,
        onBackClick = onBackClick,
        navigateToDetail = navigateToDetail,
        modifier = modifier,
        coinsUiState = coinsUiState
    )
}
