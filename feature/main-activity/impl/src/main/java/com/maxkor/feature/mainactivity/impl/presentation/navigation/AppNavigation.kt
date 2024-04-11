package com.maxkor.feature.mainactivity.impl.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.maxkor.feature.mainactivity.impl.domain.model.ReceivedCoinData
import com.maxkor.feature.mainactivity.impl.presentation.components.navbar.NavBottomBar
import com.maxkor.feature.mainactivity.impl.presentation.mapper.toReceivedCoinData
import com.maxkor.feature.mainactivity.impl.presentation.model.ReceivedCoinDataVo
import com.maxkor.feature.mainactivity.impl.presentation.viewmodel.MainActivityViewModel
import kotlinx.coroutines.delay

@Composable
internal fun AppNavigation(
    navController: NavHostController,
    coinsNavigationInteractor: CoinsNavigationInteractor,
    favoritesNavigationInteractor: FavoritesNavigationInteractor,
    detailNavigationInteractor: DetailNavigationInteractor,
    recreateApplication: () -> Unit,
    receivedCoinDataVo: ReceivedCoinDataVo? = null,
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
    val navigateToDetail: (
        name: String,
        price: String,
        imageUrl: String,
    ) -> Unit = { name, price, imageUrl ->
        detailNavigationInteractor.openScreen(
            navController = navController,
            name = name,
            price = price,
            imageUrl = imageUrl
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
                navigateToScreen = { route ->
                    when (route) {
                        CoinsFeature.ROUTE_NAME -> navigateToCoins()
                        FavoritesFeature.ROUTE_NAME -> navigateToFavorites()
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
                    navigateToDetail(name, price, imageUrl)
                },
                modifier = Modifier.padding(paddingValues)
            )

            favoritesNavigationInteractor.graph(
                navGraphBuilder = this,
                navigateToDetail = { name, price, imageUrl ->
                    navigateToDetail(name, price, imageUrl)
                },
                modifier = Modifier.padding(paddingValues)
            )

            detailNavigationInteractor.graph(
                navGraphBuilder = this,
                recreateApplication = recreateApplication,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
    receivedCoinDataVo?.let { coinData ->
        val isCoinDataValid = ReceivedCoinData.validate(
            coinData.toReceivedCoinData()
        )
        if (isCoinDataValid) {
            LaunchedEffect(key1 = null) {
                delay(CoinsFeature.LOADING_DATA_TIME)
                navigateToDetail(
                    coinData.name,
                    coinData.price,
                    coinData.imageUrl
                )
            }
        }
    }
}



