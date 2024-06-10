package com.maxkor.feature.favorites.api

import androidx.navigation.NavController

object FavoritesFeature {

    const val ROUTE_NAME = "favoritesFeature"
    const val FAVORITE_NAME = "favoritesScreen"

    fun openScreen(
        navController: NavController,
        popUpToRoute: String,
    ) = navController.navigate(
        route = ROUTE_NAME
    ) {
        popUpTo(route = popUpToRoute)
    }
}