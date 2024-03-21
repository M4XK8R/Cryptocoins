package com.maxkor.feature.coins.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.coins.impl.presentation.navigation.CoinsRoute

fun NavGraphBuilder.coinsGraph(
    navigateBack: () -> Unit,
    navigateToDetail: () -> Unit,
) {
    composable(route = CoinsFeature.ROUTE_NAME) {
        CoinsRoute(
            onBackClick = navigateBack,
            navigateToDetail = navigateToDetail
        )
    }
}