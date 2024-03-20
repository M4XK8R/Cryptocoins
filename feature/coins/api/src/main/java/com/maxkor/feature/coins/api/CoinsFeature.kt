package com.maxkor.feature.coins.api

import androidx.navigation.NavController

object CoinsFeature {

    const val ROUTE_NAME = "coinsFeature"
    const val COINS_NAME = "coinsScreen"

    fun openCoinsScreen(
        navController: NavController,
    ) = navController.navigate(ROUTE_NAME) {
        launchSingleTop = true
        popUpTo(ROUTE_NAME) {
            inclusive = true
        }
    }
}