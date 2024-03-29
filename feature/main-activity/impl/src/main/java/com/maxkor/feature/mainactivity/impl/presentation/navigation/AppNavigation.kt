package com.maxkor.feature.mainactivity.impl.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.coins.api.domain.interactor.CoinsNavigationInteractor
import com.maxkor.feature.detail.api.domain.interactor.DetailNavigationInteractor
import com.maxkor.feature.favorites.api.FavoritesFeature
import com.maxkor.feature.favorites.api.interactor.FavoritesNavigationInteractor
import com.maxkor.feature.mainactivity.impl.presentation.components.navbar.NavBottomBar
import com.maxkor.feature.mainactivity.impl.presentation.viewmodel.MainActivityViewModel

@Composable
internal fun AppNavigation(
    viewModel: MainActivityViewModel,
    navController: NavHostController,
    coinsNavigationInteractor: CoinsNavigationInteractor,
    favoritesNavigationInteractor: FavoritesNavigationInteractor,
    detailNavigationInteractor: DetailNavigationInteractor,
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
                            favoritesNavigationInteractor.openScreen(
                                navController = navController,
                                popUpToRoute = CoinsFeature.ROUTE_NAME
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = CoinsFeature.ROUTE_NAME
        ) {
            coinsNavigationInteractor.graph(
                navGraphBuilder = this,
                navigateToDetail = { name, price, imageUrl ->
                    detailNavigationInteractor.openScreen(
                        navController = navController,
                        name = name,
                        price = price,
                        imageUrl = imageUrl
                    )
                },
                modifier = Modifier.padding(paddingValues)
            )

            favoritesNavigationInteractor.graph(
                navGraphBuilder = this,
                navigateToDetail = { name, price, imageUrl ->
                    detailNavigationInteractor.openScreen(
                        navController = navController,
                        name = name,
                        price = price,
                        imageUrl = imageUrl
                    )
                },
                modifier = Modifier.padding(paddingValues)
            )

            detailNavigationInteractor.graph(
                navGraphBuilder = this,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

