package com.maxkor.feature.detail.api.domain.interactor

import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.maxkor.feature.detail.api.domain.model.DetailCoin

interface DetailNavigationInteractor {

    val nameState: MutableState<String>
    val priceState: MutableState<String>
    val imageUrlState: MutableState<String>

    fun graph(
        navGraphBuilder: NavGraphBuilder,
        recreateApplication: () -> Unit,
        informUser: (message: String) -> Unit,
        modifier: Modifier = Modifier,
    )

    fun openScreen(
        detailCoin: DetailCoin,
        navController: NavController,
    )

    companion object {
        const val EMPTY_VALUE = ""
    }
}