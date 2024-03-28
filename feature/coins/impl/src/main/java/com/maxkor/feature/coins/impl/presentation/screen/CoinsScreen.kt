package com.maxkor.feature.coins.impl.presentation.screen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkor.core.ui.composables.DataIsAbsent
import com.maxkor.core.ui.composables.Loading
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.coins.impl.presentation.model.CoinVo

@Composable
fun CoinsScreen(
    coinsUiState: CoinsUiState,
    lazyListState: LazyListState,
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
            lazyListState = lazyListState,
            navigateToDetail = navigateToDetail,
            changeFavoriteState = changeFavoriteState,
            modifier = modifier
        )
    }
}

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewCoinsScreen() {
    PreviewProvider(
        content = {
            CoinsScreen(
                coinsUiState = CoinsUiState.Loading,
                lazyListState = rememberLazyListState(),
                navigateToDetail = { _, _, _ -> },
                changeFavoriteState = {}
            )
        }
    ).DeviceRunnable()
}

