package com.maxkor.feature.favorites.impl.presentation.screen

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxkor.feature.favorites.impl.presentation.mapper.toFavoriteCoin
import com.maxkor.feature.favorites.impl.presentation.viewmodel.FavoritesViewModel

@Composable
fun FavoritesRoute(
    navigateToDetail: (
        name: String,
        price: String,
        imageUrl: String,
    ) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val favoritesUiState by viewModel.favoritesUiState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    FavoritesScreen(
        favoritesUiState = favoritesUiState,
        lazyListState = lazyListState,
        navigateToDetail = navigateToDetail,
        removeFromFavorites = { favoriteCoinVo ->
            viewModel.removeFromFavorites(
                favoriteCoinVo.toFavoriteCoin()
            )
        },
        modifier = modifier
    )
}

