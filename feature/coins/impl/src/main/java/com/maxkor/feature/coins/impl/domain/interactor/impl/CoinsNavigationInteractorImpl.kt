//package com.maxkor.feature.coins.impl.domain.interactor.impl
//
//import androidx.compose.ui.Modifier
//import androidx.lifecycle.LifecycleOwner
//import androidx.navigation.NavController
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.compose.composable
//import com.maxkor.feature.coins.api.CoinsFeature
//import com.maxkor.feature.coins.api.domain.interactor.CoinsNavigationInteractor
//import com.maxkor.feature.coins.impl.presentation.screen.CoinsRoute
//import javax.inject.Inject
//
//class CoinsNavigationInteractorImpl @Inject constructor() : CoinsNavigationInteractor {
//
//    override fun graph(
//        navGraphBuilder: NavGraphBuilder,
//        navigateToDetail: (coinName: String) -> Unit,
//        informUser: (String?) -> Unit,
//        lifecycleOwner: LifecycleOwner,
//        modifier: Modifier,
//    ) =
//        navGraphBuilder.composable(
//            route = CoinsFeature.ROUTE_NAME
//        ) {
//            CoinsRoute(
//                navigateToDetail = navigateToDetail,
//                informUser = informUser,
//                lifecycleOwner = lifecycleOwner,
//                modifier = modifier
//            )
//        }
//
//    override fun openScreen(navController: NavController) {
//        navController.popBackStack(
//            route = CoinsFeature.ROUTE_NAME,
//            inclusive = false
//        )
//    }
//}