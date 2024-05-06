package com.maxkor.feature.coins.impl.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maxkor.core.theme.LocalSpacing
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.coins.impl.R
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.presentation.components.CoinCard
import com.maxkor.feature.coins.impl.presentation.mapper.toCoinVo
import com.maxkor.feature.coins.impl.presentation.model.CoinVo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinsScreenContent(
    coinVos: List<CoinVo>,
    lazyListState: LazyListState,
    onCoinCardClick: (CoinVo) -> Unit,
    onFavoriteIconClick: (CoinVo) -> Unit,
    searchedCoin: String,
    search: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current

    Column(modifier = modifier) {
        SearchBar(
            query = searchedCoin,
            onQueryChange = { search(it) },
            onSearch = {},
            active = false,
            onActiveChange = {},
            placeholder = {
                Text(
                    text = stringResource(R.string.search)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            content = {}
        )
        Spacer(
            modifier = Modifier.size(spacing.spaceMedium)
        )
        LazyColumn(state = lazyListState) {
            items(coinVos) { coinVo ->
                CoinCard(
                    coinVo = coinVo,
                    onCardClick = { onCoinCardClick(coinVo) },
                    onFavoriteIconClick = { onFavoriteIconClick(coinVo) },
                )
            }
        }
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
            onCoinCardClick = {},
            onFavoriteIconClick = {},
            searchedCoin = "",
            search = {}
        )
    }
).DeviceRunnable()