package com.maxkor.feature.coins.impl.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.presentation.components.CoinCard
import com.maxkor.feature.coins.impl.presentation.mapper.toCoinVo
import com.maxkor.feature.coins.impl.presentation.model.CoinVo

@Composable
fun CoinsScreenContent(
    coinVos: List<CoinVo>,
    lazyListState: LazyListState,
    navigateToDetail: (
        name: String,
        price: String,
        imageUrl: String,
    ) -> Unit,
    changeFavoriteState: (CoinVo) -> Unit,
    modifier: Modifier = Modifier,
) = LazyColumn(
    state = lazyListState,
    modifier = modifier
) {
    items(coinVos) { coinVo ->
        CoinCard(
            coinVo = coinVo,
            onCardClick = {
                navigateToDetail(
                    coinVo.name,
                    coinVo.price,
                    coinVo.imageUrl
                )
            },
            onFavoriteIconClick = { changeFavoriteState(coinVo) },
        )
    }
}

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewCoinsScreenContent() = PreviewProvider(
    content = {
        CoinsScreenContent(
            coinVos = Coin.testExemplars.map { it.toCoinVo() },
            lazyListState = rememberLazyListState(),
            navigateToDetail = { _, _, _ -> },
            changeFavoriteState = {}
        )
    }
).DeviceRunnable()