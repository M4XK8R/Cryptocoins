package com.maxkor.feature.coins.api

import androidx.navigation.NavController

object CoinsFeature {

    const val ROUTE_NAME = "coinsFeature"
    const val COINS_NAME = "coinsScreen"
    const val LOADING_DATA_TIME = 2000L

    fun openScreen(navController: NavController) {
        navController.popBackStack(
            route = ROUTE_NAME,
            inclusive = false
        )
    }
}