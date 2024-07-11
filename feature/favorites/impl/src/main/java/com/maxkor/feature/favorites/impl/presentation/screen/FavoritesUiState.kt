package com.maxkor.feature.favorites.impl.presentation.screen

import com.maxkor.feature.favorites.impl.presentation.model.FavoriteCoinVo

sealed interface FavoritesUiState {
    data object Loading : FavoritesUiState
    data object NoDataAvailable : FavoritesUiState
    data class Success(val coins: List<FavoriteCoinVo>) : FavoritesUiState
}
