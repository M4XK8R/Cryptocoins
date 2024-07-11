package com.maxkor.feature.detail.api

import androidx.navigation.NavController

object DetailFeature {

    private const val ROUTE_FOR_ARGS = "detailFeature"
    const val ARG_COIN_NAME_KEY = "coin_name"
    const val ROUTE_NAME = "$ROUTE_FOR_ARGS/{$ARG_COIN_NAME_KEY}"
    const val COINS_NAME = "detailScreen"

    fun openScreen(
        coinName: String,
        navController: NavController,
    ) = navController.navigate(
        route = getRouteWithArgs(coinName)
    )

    /**
     * Private sector
     */
    private fun getRouteWithArgs(coinName: String): String =
        "$ROUTE_FOR_ARGS/$coinName"
}