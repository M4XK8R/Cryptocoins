package com.maxkor.feature.detail.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.detail.impl.presentation.navigation.DetailRoute

fun NavGraphBuilder.detailGraph(
    navigateBack: () -> Unit,
) {
    composable(route = DetailFeature.ROUTE_NAME) {
        DetailRoute(
            onBackClick = navigateBack
        )
    }
}