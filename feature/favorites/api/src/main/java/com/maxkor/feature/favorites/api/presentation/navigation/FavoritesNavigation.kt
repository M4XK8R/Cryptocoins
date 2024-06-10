package com.maxkor.feature.favorites.api.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder

interface FavoritesNavigation {

    fun graph(
        navGraphBuilder: NavGraphBuilder,
        onFavoriteCardClick: (coinId: String) -> Unit,
        modifier: Modifier = Modifier,
    )
}