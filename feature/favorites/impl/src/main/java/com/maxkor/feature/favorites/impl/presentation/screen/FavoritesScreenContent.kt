package com.maxkor.feature.favorites.impl.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.favorites.impl.domain.model.FavoriteCoin
import com.maxkor.feature.favorites.impl.presentation.components.FavoriteCoinCard
import com.maxkor.feature.favorites.impl.presentation.mapper.toFavoriteCoinVo
import com.maxkor.feature.favorites.impl.presentation.model.FavoriteCoinVo

@Composable
fun FavoritesScreenContent(
    favoriteCoinsVos: List<FavoriteCoinVo>,
    lazyListState: LazyListState,
    onFavoriteCardClick: (coinName: String) -> Unit,
    onFavoriteIconClick: (FavoriteCoinVo) -> Unit,
    modifier: Modifier = Modifier,
) = LazyColumn(
    state = lazyListState,
    modifier = modifier
) {
    items(favoriteCoinsVos) { favoriteCoinVo ->
        FavoriteCoinCard(
            favoriteCoinVo = favoriteCoinVo,
            onCardClick = { onFavoriteCardClick(favoriteCoinVo.name) },
            onFavoriteIconClick = { onFavoriteIconClick(favoriteCoinVo) }
        )
    }
}

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewFavoritesScreenContent() = PreviewProvider(
    content = {
        FavoritesScreenContent(
            favoriteCoinsVos = FavoriteCoin.testExemplars
                .map { it.toFavoriteCoinVo() },
            lazyListState = rememberLazyListState(),
            onFavoriteCardClick = {},
            onFavoriteIconClick = {}
        )
    }
).DeviceRunnable()