package com.maxkor.feature.coins.impl.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkor.core.theme.CryptocoinsTheme
import com.maxkor.core.ui.composables.DataIsAbsent
import com.maxkor.core.ui.composables.Loading
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.DarkPreview
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.presentation.components.CoinCard
import com.maxkor.feature.coins.impl.presentation.mapper.toCoinVo
import com.maxkor.feature.coins.impl.presentation.model.CoinVo

@Composable
fun CoinsScreen(
    coinsUiState: CoinsUiState,
    navigateToDetail: (
        name: String,
        price: String,
        imageUrl: String,
    ) -> Unit,
    changeFavoriteState: (CoinVo) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (coinsUiState) {
        is CoinsUiState.Loading -> Loading()
        is CoinsUiState.NoDataAvailable -> DataIsAbsent()
        is CoinsUiState.Success -> CoinsScreenContent(
            coinVos = coinsUiState.coins,
            navigateToDetail = navigateToDetail,
            changeFavoriteState = changeFavoriteState,
            modifier = modifier
        )
    }
}

@Composable
private fun CoinsScreenContent(
    coinVos: List<CoinVo>,
    navigateToDetail: (
        name: String,
        price: String,
        imageUrl: String,
    ) -> Unit,
    changeFavoriteState: (CoinVo) -> Unit,
    modifier: Modifier = Modifier,
) {
    CryptocoinsTheme {
        LazyColumn {
            items(coinVos) { cryptocoinVo ->
                CoinCard(
                    coinVo = cryptocoinVo,
                    navigateToDetail = navigateToDetail,
                    changeFavoriteState = changeFavoriteState,
                    modifier = modifier
                )
            }
        }
    }
}

/**
 * Preview
 */
@Composable
@DarkPreview
fun RunPreviewCoinsScreen() {
    PreviewProvider(
        content = {
            CoinsScreen(
                coinsUiState = CoinsUiState.Success(
                    coins = Coin.testExemplars.map { it.toCoinVo() }
                ),
                navigateToDetail = { _, _, _ -> },
                changeFavoriteState = {}
            )
        }
    ).DeviceRunnable()
}

