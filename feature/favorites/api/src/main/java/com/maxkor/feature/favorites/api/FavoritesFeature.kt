package com.maxkor.feature.favorites.api

import androidx.navigation.NavController

object FavoritesFeature {

    const val ROUTE_NAME = "favoritesFeature"
    const val FAVORITE_NAME = "favoritesScreen"

    fun openFavoritesScreen(
        navController: NavController,
        routePopUpTo: String,
    ) = navController.navigate(ROUTE_NAME) {
        launchSingleTop = true
        popUpTo(routePopUpTo) {
            inclusive = false
        }
    }
}