package com.maxkor.feature.coins.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.coins.api.presentation.navigation.CoinsNavigation
import com.maxkor.feature.coins.impl.presentation.screen.CoinsRoute
import javax.inject.Inject

class CoinsNavigationImpl @Inject constructor() : CoinsNavigation {

    override fun graph(
        navGraphBuilder: NavGraphBuilder,
        onCoinCardClick: (coinName: String) -> Unit,
        informUser: (String?) -> Unit,
        lifecycleOwner: LifecycleOwner,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(
            route = CoinsFeature.ROUTE_NAME
        ) {
            CoinsRoute(
                onCoinCardClick = onCoinCardClick,
                informUser = informUser,
                lifecycleOwner = lifecycleOwner,
                modifier = modifier
            )
        }
    }
}