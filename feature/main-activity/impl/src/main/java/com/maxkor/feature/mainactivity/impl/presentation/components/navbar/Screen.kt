package com.maxkor.feature.mainactivity.impl.presentation.components.navbar

import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.favorites.api.FavoritesFeature

enum class Screen(val route: String) {
    COINS(CoinsFeature.ROUTE_NAME),
    DETAIL(CoinsFeature.ROUTE_NAME),
    FAVORITES(FavoritesFeature.ROUTE_NAME)
}