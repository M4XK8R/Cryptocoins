package com.maxkor.feature.mainactivity.impl.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.maxkor.feature.coins.api.CoinsFeature
import com.maxkor.feature.coins.api.coinsGraph
import com.maxkor.feature.mainactivity.impl.presentation.viewmodel.MainActivityViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AppNavigation(
    viewModel: MainActivityViewModel,
    navController: NavHostController
) {
    Scaffold(

    ) {
        NavHost(
            navController = navController,
            startDestination = CoinsFeature.ROUTE_NAME
        ) {
            coinsGraph (navigateBack = {})
        }
    }
}

