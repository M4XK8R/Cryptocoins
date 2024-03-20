package com.maxkor.feature.detail.api

import androidx.navigation.NavController

object DetailFeature {

    const val ROUTE_NAME = "detailFeature"
    const val COINS_NAME = "detailScreen"

    fun openDetailScreen(navController: NavController) =
        navController.navigate(ROUTE_NAME)
}