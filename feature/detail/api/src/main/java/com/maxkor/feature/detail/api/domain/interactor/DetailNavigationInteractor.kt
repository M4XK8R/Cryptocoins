package com.maxkor.feature.detail.api.domain.interactor

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface DetailNavigationInteractor {

    fun graph(
        navGraphBuilder: NavGraphBuilder,
        recreateApplication: () -> Unit,
        informUser: (message: String) -> Unit,
        modifier: Modifier = Modifier,
    )

    fun openScreen(
        coinName: String,
        navController: NavController,
    )
}