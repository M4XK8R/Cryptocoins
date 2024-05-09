package com.maxkor.feature.favorites.impl.presentation.contract

import com.maxkor.feature.favorites.impl.presentation.model.FavoriteCoinVo


sealed class FavoritesEvents {
    data class OnFavoriteIconClick(
        val favoriteCoinVo: FavoriteCoinVo,
    ) : FavoritesEvents()

    data class OnFavoriteCardClick(
        val coinName: String,
    ) : FavoritesEvents()
}