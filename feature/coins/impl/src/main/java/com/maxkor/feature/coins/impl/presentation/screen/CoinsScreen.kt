package com.maxkor.feature.coins.impl.presentation.screen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.maxkor.core.base.R
import com.maxkor.core.ui.components.composables.DataIsAbsent
import com.maxkor.core.ui.components.composables.Loading
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
    searchedCoin: String,
    search: (String) -> Unit,
    filterCoinsVos: (List<CoinVo>) -> List<CoinVo>,
    modifier: Modifier = Modifier,
) = when (coinsUiState) {
    is CoinsUiState.Loading -> Loading()
    is CoinsUiState.NoDataAvailable -> DataIsAbsent(
        text = stringResource(id = R.string.no_valid_data)
    )

    is CoinsUiState.Success -> CoinsScreenContent(
        coinVos = filterCoinsVos(coinsUiState.coinsVos),
        lazyListState = lazyListState,
        navigateToDetail = navigateToDetail,
        changeFavoriteState = changeFavoriteState,
        searchedCoin = searchedCoin,
        search = search,
        modifier = modifier
    )
}

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewCoinsScreen() = PreviewProvider(
    content = {
        CoinsScreen(
            coinsUiState = CoinsUiState.Loading,
            lazyListState = rememberLazyListState(),
            navigateToDetail = { _, _, _ -> },
            changeFavoriteState = {},
            searchedCoin = "",
            search = {},
            filterCoinsVos = { it }
        )
    }
).DeviceRunnable()

