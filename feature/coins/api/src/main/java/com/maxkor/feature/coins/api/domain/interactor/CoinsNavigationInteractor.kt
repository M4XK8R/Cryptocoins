package com.maxkor.feature.coins.api.domain.interactor

import androidx.compose.ui.Modifier
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
        onErrorMessage: (String?) -> Unit,
        modifier: Modifier = Modifier,
    )

    fun openScreen(navController: NavController)
}