package com.maxkor.feature.coins.impl.domain.interactor.api

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.coins.api.domain.interactor.CoinsNavigationInteractor
import com.maxkor.feature.coins.impl.presentation.navigation.CoinsRoute
import javax.inject.Inject

class NavigationInteractorImpl @Inject constructor() : CoinsNavigationInteractor {

    override fun graph(
        navGraphBuilder: NavGraphBuilder,
        navigateToDetail: (
            name: String,
            price: String,
            imageUrl: String,
        ) -> Unit,
    ) = navGraphBuilder.composable(route = CoinsFeature.ROUTE_NAME) {
        CoinsRoute(navigateToDetail = navigateToDetail)
    }

    override fun openScreen(navController: NavController) {
        navController.popBackStack()
    }
}