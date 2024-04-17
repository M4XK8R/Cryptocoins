package com.maxkor.feature.detail.api.domain.interactor

import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface DetailNavigationInteractor {

    val nameState: MutableState<String>
    val priceState: MutableState<String>
    val imageUrlState: MutableState<String>

    fun graph(
        navGraphBuilder: NavGraphBuilder,
        recreateApplication: () -> Unit,
        informUser: (message: String) -> Unit,
        modifier: Modifier = Modifier,
        name: String = nameState.value,
        price: String = priceState.value,
        imageUrl: String = imageUrlState.value,
    )

    fun openScreen(
        navController: NavController,
        name: String,
        price: String,
        imageUrl: String,
    )
}