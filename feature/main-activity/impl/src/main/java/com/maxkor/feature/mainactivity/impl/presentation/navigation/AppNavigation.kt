package com.maxkor.feature.mainactivity.impl.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.coins.api.domain.interactor.CoinsNavigationInteractor
import com.maxkor.feature.detail.api.domain.interactor.DetailNavigationInteractor
import com.maxkor.feature.favorites.api.FavoritesFeature
import com.maxkor.feature.favorites.api.interactor.FavoritesNavigationInteractor
import com.maxkor.feature.mainactivity.impl.presentation.components.NavBottomBar
import kotlinx.coroutines.delay

@Composable
internal fun AppNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    coinsNavigationInteractor: CoinsNavigationInteractor,
    favoritesNavigationInteractor: FavoritesNavigationInteractor,
    detailNavigationInteractor: DetailNavigationInteractor,
    showSnackbarMessage: (String) -> Unit,
    recreateApplication: () -> Unit,
    receivedCoinName: String? = null,
) {
    val navigateToCoins: () -> Unit = {
        coinsNavigationInteractor.openScreen(navController)
    }
    val navigateToFavorites: () -> Unit = {
        favoritesNavigationInteractor.openScreen(
            navController = navController,
            popUpToRoute = CoinsFeature.ROUTE_NAME
        )
    }
    val navigateToDetail: (coinName: String) -> Unit = { coinName ->
        detailNavigationInteractor.openScreen(
            coinName = coinName,
            navController = navController,
        )
    }
    val currentRoute = navController.currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    Scaffold(
        bottomBar = {
            NavBottomBar(
                currentRoute = currentRoute,
                navigationItems = listOf(
                    BottomNavigationItem.Home,
                    BottomNavigationItem.Favorite
                ),
                navigateToScreen = { route ->
                    when (route) {
                        CoinsFeature.ROUTE_NAME -> navigateToCoins()
                        FavoritesFeature.ROUTE_NAME -> navigateToFavorites()
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = CoinsFeature.ROUTE_NAME
        ) {
            coinsNavigationInteractor.graph(
                navGraphBuilder = this,
                navigateToDetail = { coinName ->
                    navigateToDetail(coinName)
                },
                informUser = { message ->
                    message?.let(showSnackbarMessage)
                },
                modifier = Modifier.padding(paddingValues)
            )

            favoritesNavigationInteractor.graph(
                navGraphBuilder = this,
                navigateToDetail = { coinName ->
                    navigateToDetail(coinName)
                },
                modifier = Modifier.padding(paddingValues)
            )

            detailNavigationInteractor.graph(
                navGraphBuilder = this,
                recreateApplication = recreateApplication,
                informUser = { message ->
                    showSnackbarMessage(message)
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
    receivedCoinName?.let { coinName ->
        LaunchedEffect(key1 = null) {
            delay(CoinsFeature.LOADING_DATA_TIME)
            navigateToDetail(coinName)
        }
    }
}



