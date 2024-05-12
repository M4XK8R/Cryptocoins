package com.maxkor.feature.coins.api.domain.interactor

import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface CoinsNavigationInteractor {

    fun graph(
        navGraphBuilder: NavGraphBuilder,
        navigateToDetail: (coinName: String) -> Unit,
        informUser: (String?) -> Unit,
        lifecycleOwner: LifecycleOwner,
        modifier: Modifier = Modifier,
    )

    fun openScreen(navController: NavController)
}