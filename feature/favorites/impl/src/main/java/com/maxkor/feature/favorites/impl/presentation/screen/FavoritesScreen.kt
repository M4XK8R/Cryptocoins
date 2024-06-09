package com.maxkor.feature.favorites.impl.presentation.screen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.maxkor.core.ui.components.DataIsAbsent
import com.maxkor.core.ui.components.Loading
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.favorites.impl.R
import com.maxkor.feature.favorites.impl.presentation.model.FavoriteCoinVo

@Composable
fun FavoritesScreen(
    favoritesUiState: FavoritesUiState,
    lazyListState: LazyListState,
    navigateToDetail: (coinId: String) -> Unit,
    removeFromFavorites: (FavoriteCoinVo) -> Unit,
    modifier: Modifier = Modifier,
) = when (favoritesUiState) {
    is FavoritesUiState.Loading -> Loading()
    is FavoritesUiState.NoDataAvailable -> DataIsAbsent(
        text = stringResource(R.string.favorites_list_is_empty)
    )
    is FavoritesUiState.Success -> FavoritesScreenContent(
        favoriteCoinsVos = favoritesUiState.coins,
        lazyListState = lazyListState,
        onFavoriteCardClick = navigateToDetail,
        onFavoriteIconClick = removeFromFavorites,
        modifier = modifier
    )
}

/**
 * Preview
 */
@Composable
@RawPreview
private fun RunPreviewFavoritesScreen() = PreviewProvider(
    content = {
        FavoritesScreen(
            favoritesUiState = FavoritesUiState.NoDataAvailable,
            lazyListState = rememberLazyListState(),
            navigateToDetail = {},
            removeFromFavorites = {}
        )
    }
).DeviceRunnable()