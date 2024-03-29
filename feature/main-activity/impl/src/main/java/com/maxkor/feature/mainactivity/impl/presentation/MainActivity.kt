package com.maxkor.feature.mainactivity.impl.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.maxkor.core.theme.CryptocoinsTheme
import com.maxkor.feature.coins.api.domain.interactor.CoinsNavigationInteractor
import com.maxkor.feature.favorites.api.interactor.FavoritesNavigationInteractor
import com.maxkor.feature.mainactivity.impl.presentation.navigation.AppNavigation
import com.maxkor.feature.mainactivity.impl.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var coinsNavigationInteractor: CoinsNavigationInteractor

    @Inject
    lateinit var favoritesNavigationInteractor: FavoritesNavigationInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: MainActivityViewModel = hiltViewModel()
            val navController = rememberNavController()
            CryptocoinsTheme {
                AppNavigation(
                    viewModel = viewModel,
                    navController = navController,
                    coinsNavigationInteractor = coinsNavigationInteractor,
                    favoritesNavigationInteractor = favoritesNavigationInteractor
                )
            }
        }
    }
}