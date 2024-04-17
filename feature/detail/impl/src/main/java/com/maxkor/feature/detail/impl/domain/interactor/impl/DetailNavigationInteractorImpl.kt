package com.maxkor.feature.detail.impl.domain.interactor.impl

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxkor.feature.detail.api.DetailFeature
import com.maxkor.feature.detail.api.domain.interactor.DetailNavigationInteractor
import com.maxkor.feature.detail.impl.domain.model.DetailCoin
import com.maxkor.feature.detail.impl.domain.preferences.DetailPreferences
import com.maxkor.feature.detail.impl.presentation.mapper.toDetailCoinVo
import com.maxkor.feature.detail.impl.presentation.screen.DetailRoute
import javax.inject.Inject

class DetailNavigationInteractorImpl @Inject constructor(
    private val detailPreferences: DetailPreferences,
) : DetailNavigationInteractor {

    override val nameState: MutableState<String> =
        mutableStateOf(DetailNavigationInteractor.EMPTY_VALUE)

    override val priceState: MutableState<String> =
        mutableStateOf(DetailNavigationInteractor.EMPTY_VALUE)

    override val imageUrlState: MutableState<String> =
        mutableStateOf(DetailNavigationInteractor.EMPTY_VALUE)

    override fun graph(
        navGraphBuilder: NavGraphBuilder,
        recreateApplication: () -> Unit,
        informUser: (message: String) -> Unit,
        modifier: Modifier,
        name: String,
        price: String,
        imageUrl: String,
    ) = navGraphBuilder.composable(route = DetailFeature.ROUTE_NAME) {
        val extraCoinInfo = detailPreferences.getExtraCoinInfo(key = name)
        val detailCoin = DetailCoin(
            name = name,
            price = price,
            imageUrl = imageUrl,
            extraInfo = extraCoinInfo
        )
        DetailRoute(
            detailCoinVo = detailCoin.toDetailCoinVo(),
            modifier = modifier,
            recreateApplication = recreateApplication,
            informUser = informUser
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