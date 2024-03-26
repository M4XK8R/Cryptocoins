package com.maxkor.feature.coins.api.domain.interactor

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface CoinsNavigationInteractor {

    fun graph(
        navGraphBuilder: NavGraphBuilder,
        navigateToDetail: (
            name: String,
            price: String,
            imageUrl: String,
        ) -> Unit,
    )

    fun openScreen(navController: NavController)
}