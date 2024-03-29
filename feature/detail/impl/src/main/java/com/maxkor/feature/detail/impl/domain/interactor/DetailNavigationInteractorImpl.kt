package com.maxkor.feature.detail.impl.domain.interactor

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.detail.api.DetailFeature
import com.maxkor.feature.detail.api.domain.interactor.DetailNavigationInteractor
import com.maxkor.feature.detail.impl.presentation.screen.DetailRoute
import javax.inject.Inject

class DetailNavigationInteractorImpl @Inject constructor() : DetailNavigationInteractor {

    override val nameState: MutableState<String> =
        mutableStateOf("Unknown")

    override val priceState: MutableState<String> =
        mutableStateOf("Unknown")

    override val imageUrlState: MutableState<String> =
        mutableStateOf("Unknown")

    override fun graph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
        name: String,
        price: String,
        imageUrl: String,
    ) = navGraphBuilder.composable(route = DetailFeature.ROUTE_NAME) {
        DetailRoute(
            name = name,
            price = price,
            imageUrl = imageUrl,
            modifier = modifier
        )
    }

    override fun openScreen(
        navController: NavController,
        name: String,
        price: String,
        imageUrl: String,
    ) {
        navController.navigate(route = DetailFeature.ROUTE_NAME)
        nameState.value = name
        priceState.value = price
        imageUrlState.value = imageUrl
    }
}