package com.maxkor.feature.detail.impl.domain.interactor.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.detail.api.DetailFeature
import com.maxkor.feature.detail.api.domain.interactor.DetailNavigationInteractor
import com.maxkor.feature.detail.impl.domain.preferences.DetailPreferences
import com.maxkor.feature.detail.impl.presentation.screen.DetailRoute
import javax.inject.Inject

class DetailNavigationInteractorImpl @Inject constructor(
    private val detailPreferences: DetailPreferences,
) : DetailNavigationInteractor {

    override fun graph(
        navGraphBuilder: NavGraphBuilder,
        recreateApplication: () -> Unit,
        informUser: (message: String) -> Unit,
        modifier: Modifier,
    ) = navGraphBuilder.composable(
        route = DetailFeature.ROUTE_NAME
    ) { backStackEntry ->
        val coinName = backStackEntry.arguments
            ?.getString(DetailFeature.ARG_COIN_NAME_KEY, null) ?: ""
        val coinExtraInfo = detailPreferences.getExtraCoinInfo(coinName)

        DetailRoute(
            coinName = coinName,
            coinExtraInfo = coinExtraInfo,
            modifier = modifier,
            recreateApplication = recreateApplication,
            informUser = informUser
        )
    }

    override fun openScreen(
        coinName: String,
        navController: NavController,
    ) = navController.navigate(
        route = DetailFeature.getRouteWithArgs(coinName)
    )
}