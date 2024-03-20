package com.maxkor.feature.favorites.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.favorites.impl.presentation.navigation.FavoritesRoute

fun NavGraphBuilder.favoritesGraph(
    navigateBack: () -> Unit,
    navigateToDetail: () -> Unit,
) {
    composable(route = FavoritesFeature.ROUTE_NAME) {
        FavoritesRoute(
            onBackClick = navigateBack,
            navigateToDetail = navigateToDetail
        )
    }
}