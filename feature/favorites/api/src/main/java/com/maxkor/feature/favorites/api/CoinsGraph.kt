package com.maxkor.feature.favorites.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.favorites.impl.presentation.navigation.FavoritesRoute

fun NavGraphBuilder.favoritesGraph(
    navigateBack: () -> Unit,
) {
//    dialog(
//        route = FavoritesFeature.ROUTE_NAME,
//        dialogProperties = DialogProperties(
//            usePlatformDefaultWidth = false
//        )
    composable(route = FavoritesFeature.ROUTE_NAME) {
        FavoritesRoute(
            onBackClick = navigateBack
        )
    }
}