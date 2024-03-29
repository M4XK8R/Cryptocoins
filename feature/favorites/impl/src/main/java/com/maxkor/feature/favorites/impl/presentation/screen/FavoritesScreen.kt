package com.maxkor.feature.favorites.impl.presentation.screen

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkor.core.ui.composables.DataIsAbsent
import com.maxkor.core.ui.composables.Loading
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.favorites.impl.presentation.model.FavoriteCoinVo

@Composable
internal fun FavoritesScreen(
    favoritesUiState: FavoritesUiState,
    lazyListState: LazyListState,
    navigateToDetail: (
        name: String,
        price: String,
        imageUrl: String,
    ) -> Unit,
    removeFromFavorites: (FavoriteCoinVo) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (favoritesUiState) {
        is FavoritesUiState.Loading -> Loading()
        is FavoritesUiState.NoDataAvailable -> DataIsAbsent(text = "Favorites list is empty")
        is FavoritesUiState.Success -> FavoritesScreenContent(
            favoriteCoinsVos = favoritesUiState.coins,
            lazyListState = lazyListState,
            navigateToDetail = navigateToDetail,
            removeFromFavorites = removeFromFavorites,
            modifier = modifier
        )
    }
}

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewFavoritesScreen() {
    PreviewProvider(
        content = {
            FavoritesScreen(
                favoritesUiState = FavoritesUiState.NoDataAvailable,
                lazyListState = rememberLazyListState(),
                navigateToDetail = { _, _, _ -> },
                removeFromFavorites = {}
            )
        }
    ).DeviceRunnable()
}