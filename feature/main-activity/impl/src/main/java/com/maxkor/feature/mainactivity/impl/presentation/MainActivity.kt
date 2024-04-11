package com.maxkor.feature.mainactivity.impl.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.maxkor.core.theme.CryptocoinsTheme
import com.maxkor.feature.coins.api.domain.interactor.CoinsNavigationInteractor
import com.maxkor.feature.detail.api.broadcast.DetailFeatureReceiver
import com.maxkor.feature.detail.api.domain.interactor.DetailNavigationInteractor
import com.maxkor.feature.favorites.api.interactor.FavoritesNavigationInteractor
import com.maxkor.feature.mainactivity.impl.domain.model.ReceivedCoinData
import com.maxkor.feature.mainactivity.impl.presentation.mapper.toReceivedCoinDataVo
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

    @Inject
    lateinit var detailNavigationInteractor: DetailNavigationInteractor

    private var receivedCoinData: ReceivedCoinData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let { intent ->
            with(intent) {
                if (action == DetailFeatureReceiver.ACTION_SHOW_REQUIRED_SCREEN) {
                    receivedCoinData = ReceivedCoinData(
                        name = getStringExtra(DetailFeatureReceiver.COIN_NAME_PARAM),
                        price = getStringExtra(DetailFeatureReceiver.COIN_PRICE_PARAM),
                        imageUrl = getStringExtra(DetailFeatureReceiver.COIN_IMAGE_URL_PARAM),
                    )
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            val viewModel: MainActivityViewModel = hiltViewModel()
            val navController = rememberNavController()
            CryptocoinsTheme {
                AppNavigation(
                    navController = navController,
                    coinsNavigationInteractor = coinsNavigationInteractor,
                    favoritesNavigationInteractor = favoritesNavigationInteractor,
                    detailNavigationInteractor = detailNavigationInteractor,
                    recreateApplication = ::recreateApplication,
                    receivedCoinDataVo = receivedCoinData?.toReceivedCoinDataVo()
                )
            }
        }
    }

    private fun recreateApplication() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
        Runtime.getRuntime().exit(0)
    }
}