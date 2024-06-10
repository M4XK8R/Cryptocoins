package com.maxkor.feature.favorites.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.favorites.api.FavoritesFeature
import com.maxkor.feature.favorites.api.presentation.navigation.FavoritesNavigation
import com.maxkor.feature.favorites.impl.presentation.screen.FavoritesRoute
import javax.inject.Inject

class FavoritesNavigationImpl @Inject constructor() : FavoritesNavigation {

    override fun graph(
        navGraphBuilder: NavGraphBuilder,
        onFavoriteCardClick: (coinId: String) -> Unit,
        modifier: Modifier,
    ) = navGraphBuilder.composable(
        route = FavoritesFeature.ROUTE_NAME
    ) {
        FavoritesRoute(
            onFavoriteCardClick = onFavoriteCardClick,
            modifier = modifier
        )
    }
}
