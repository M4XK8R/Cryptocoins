package com.maxkor.feature.mainactivity.impl.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.coins.api.presentation.navigation.CoinsNavigation
import com.maxkor.feature.detail.api.DetailFeature
import com.maxkor.feature.detail.api.presentation.navigation.DetailNavigation
import com.maxkor.feature.favorites.api.FavoritesFeature
import com.maxkor.feature.favorites.api.presentation.navigation.FavoritesNavigation
import com.maxkor.feature.mainactivity.impl.presentation.components.NavBottomBar
import com.maxkor.feature.mainactivity.impl.presentation.ktx.isDataNotUnknown
import com.maxkor.feature.mainactivity.impl.presentation.model.ReceivedCoinDataVo
import kotlinx.coroutines.delay

@Composable
internal fun AppNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    coinsNavigation: CoinsNavigation,
    favoritesNavigation: FavoritesNavigation,
    detailNavigation: DetailNavigation,
    showSnackbarMessage: (String) -> Unit,
    recreateApplication: () -> Unit,
    receivedCoinDataVoNullable: ReceivedCoinDataVo? = null,
) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    val navigateToCoins: () -> Unit = {
        CoinsFeature.openScreen(navController)
    }

    val navigateToFavorites: () -> Unit = {
        FavoritesFeature.openScreen(
            navController = navController,
            popUpToRoute = CoinsFeature.ROUTE_NAME
        )
    }

    val navigateToDetail: (coinName: String) -> Unit = { coinName ->
        DetailFeature.openScreen(
            coinName = coinName,
            navController = navController,
        )
    }

    val currentRoute = navController.currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    receivedCoinDataVoNullable?.let { receivedCoinDataVo ->
        if (receivedCoinDataVo.isDataNotUnknown()) {
            LaunchedEffect(key1 = true) {
                delay(CoinsFeature.LOADING_IMITATION_TIME)
                navigateToDetail(receivedCoinDataVo.name)
            }
        }
    }

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
            coinsNavigation.graph(
                navGraphBuilder = this,
                onCoinCardClick = { coinName ->
                    navigateToDetail(coinName)
                },
                informUser = { message ->
                    message?.let(showSnackbarMessage)
                },
                lifecycleOwner = lifecycleOwner,
                modifier = Modifier.padding(paddingValues)
            )

            favoritesNavigation.graph(
                navGraphBuilder = this,
                onFavoriteCardClick = { coinName ->
                    navigateToDetail(coinName)
                },
                modifier = Modifier.padding(paddingValues)
            )

            detailNavigation.graph(
                navGraphBuilder = this,
                recreateApplication = recreateApplication,
                informUser = { message ->
                    showSnackbarMessage(message)
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}



