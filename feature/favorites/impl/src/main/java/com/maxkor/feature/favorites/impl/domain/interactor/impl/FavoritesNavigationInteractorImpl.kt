package com.maxkor.feature.favorites.impl.domain.interactor.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.favorites.api.FavoritesFeature
import com.maxkor.feature.favorites.api.interactor.FavoritesNavigationInteractor
import com.maxkor.feature.favorites.impl.presentation.screen.FavoritesRoute
import javax.inject.Inject

class FavoritesNavigationInteractorImpl @Inject constructor() : FavoritesNavigationInteractor {

    override fun graph(
        navGraphBuilder: NavGraphBuilder,
        navigateToDetail: (coinId: String) -> Unit,
        modifier: Modifier,
    ) = navGraphBuilder.composable(route = FavoritesFeature.ROUTE_NAME) {
        FavoritesRoute(
            navigateToDetail = navigateToDetail,
            modifier = modifier
        )
    }

    override fun openScreen(
        navController: NavController,
        popUpToRoute: String,
    ) {
        navController.navigate(route = FavoritesFeature.ROUTE_NAME) {
            popUpTo(route = popUpToRoute)
        }
    }
}
