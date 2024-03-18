package com.maxkor.feature.coins.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.coins.impl.presentation.navigation.CoinsRoute

fun NavGraphBuilder.coinsGraph(
    navigateBack: () -> Unit,
) {
//    dialog(
//        route = CoinsFeature.ROUTE_NAME,
//        dialogProperties = DialogProperties(
//            usePlatformDefaultWidth = false
//        )
    composable(route = CoinsFeature.ROUTE_NAME) {
        CoinsRoute(
            onBackClick = navigateBack
        )
    }
}