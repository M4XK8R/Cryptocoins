package com.maxkor.feature.coins.impl.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxkor.feature.coins.impl.presentation.mapper.toCoin
import com.maxkor.feature.coins.impl.presentation.screen.CoinsScreen
import com.maxkor.feature.coins.impl.presentation.viewmodel.CoinsViewModel

@Composable
fun CoinsRoute(
    navigateToDetail: (
        name: String,
        price: String,
        imageUrl: String,
    ) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: CoinsViewModel = hiltViewModel()
    val coinsUiState by viewModel.coinsUiState.collectAsStateWithLifecycle()

    CoinsScreen(
        coinsUiState = coinsUiState,
        navigateToDetail = navigateToDetail,
        changeFavoriteState = { viewModel.changeCoinFavoriteState(it.toCoin()) },
        modifier = modifier
    )
}
