package com.maxkor.feature.favorites.api

import androidx.navigation.NavController

object FavoritesFeature {

    const val ROUTE_NAME = "favoritesFeature"
    const val COINS_NAME = "favoritesScreen"

    fun openFavoritesScreen(navController: NavController) =
        navController.navigate(ROUTE_NAME)
}