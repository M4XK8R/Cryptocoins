package com.maxkor.feature.favorites.api.interactor

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface FavoritesNavigationInteractor {

    fun graph(
        navGraphBuilder: NavGraphBuilder,
        navigateToDetail: (coinId: String) -> Unit,
        modifier: Modifier = Modifier,
    )

    fun openScreen(
        navController: NavController,
        popUpToRoute: String,
    )
}