package com.maxkor.feature.coins.impl.domain.interactor

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.coins.api.domain.interactor.CoinsNavigationInteractor
import com.maxkor.feature.coins.impl.presentation.navigation.CoinsRoute
import javax.inject.Inject

class CoinsApiNavigationInteractorImpl @Inject constructor() : CoinsNavigationInteractor {

    override fun graph(
        navGraphBuilder: NavGraphBuilder,
        navigateBack: () -> Unit,
        navigateToDetail: () -> Unit,
    ) = navGraphBuilder.composable(route = CoinsFeature.ROUTE_NAME) {
        CoinsRoute(
            onBackClick = navigateBack,
            navigateToDetail = navigateToDetail
        )
    }

    override fun openScreen(navController: NavController) =
        navController.navigate(CoinsFeature.ROUTE_NAME) {
            launchSingleTop = true
            popUpTo(CoinsFeature.ROUTE_NAME) {
                inclusive = true
            }
        }
}