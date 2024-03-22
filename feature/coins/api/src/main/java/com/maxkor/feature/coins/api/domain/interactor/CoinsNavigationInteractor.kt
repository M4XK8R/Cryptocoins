package com.maxkor.feature.coins.api.domain.interactor

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface CoinsNavigationInteractor {

    fun graph(
        navGraphBuilder: NavGraphBuilder,
        navigateBack: () -> Unit,
        navigateToDetail: () -> Unit,
    )

    fun openScreen(navController: NavController)
}