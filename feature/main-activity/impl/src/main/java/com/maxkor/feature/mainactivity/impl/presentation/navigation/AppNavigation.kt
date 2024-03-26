package com.maxkor.feature.mainactivity.impl.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.coins.api.domain.interactor.CoinsNavigationInteractor
import com.maxkor.feature.detail.api.DetailFeature
import com.maxkor.feature.detail.api.detailGraph
import com.maxkor.feature.favorites.api.FavoritesFeature
import com.maxkor.feature.favorites.api.favoritesGraph
import com.maxkor.feature.mainactivity.impl.presentation.components.navbar.NavBottomBar
import com.maxkor.feature.mainactivity.impl.presentation.viewmodel.MainActivityViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AppNavigation(
    viewModel: MainActivityViewModel,
    navController: NavHostController,
    coinsNavigationInteractor: CoinsNavigationInteractor,
) {
    val currentRoute = navController.currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    Scaffold(
        bottomBar = {
            NavBottomBar(
                currentRoute = currentRoute,
                navigateToScreen = { route ->
                    when (route) {
                        CoinsFeature.ROUTE_NAME -> {
                            coinsNavigationInteractor.openScreen(
                                navController = navController
                            )
                        }

                        FavoritesFeature.ROUTE_NAME -> {
                            FavoritesFeature.openFavoritesScreen(
                                navController = navController,
                                routePopUpTo = CoinsFeature.ROUTE_NAME
                            )
                        }
                    }
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = CoinsFeature.ROUTE_NAME
        ) {
            coinsNavigationInteractor.graph(
                navGraphBuilder = this,
                navigateToDetail = { name, price, imageUrl ->
                    DetailFeature.openDetailScreen(navController)
                }
            )
            favoritesGraph(
                navigateBack = {},
                navigateToDetail = { DetailFeature.openDetailScreen(navController) }
            )
            detailGraph(navigateBack = {})
        }
    }
}

