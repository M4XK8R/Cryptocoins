package com.maxkor.feature.mainactivity.impl.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.maxkor.core.theme.CryptocoinsTheme
import com.maxkor.feature.coins.api.presentation.navigation.CoinsNavigation
import com.maxkor.feature.detail.api.presentation.broadcast.DetailFeatureReceiver
import com.maxkor.feature.detail.api.presentation.navigation.DetailNavigation
import com.maxkor.feature.favorites.api.presentation.navigation.FavoritesNavigation
import com.maxkor.feature.mainactivity.impl.presentation.model.ReceivedCoinDataVo
import com.maxkor.feature.mainactivity.impl.presentation.navigation.AppNavigation
import com.maxkor.feature.mainactivity.impl.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var coinsNavigation: CoinsNavigation

    @Inject
    lateinit var favoritesNavigation: FavoritesNavigation

    @Inject
    lateinit var detailNavigation: DetailNavigation

    private var receivedCoinDataVoNullable: ReceivedCoinDataVo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let { nonNullIntent ->
            with(nonNullIntent) {
                if (action == DetailFeatureReceiver.ACTION_SHOW_REQUIRED_SCREEN) {
                    receivedCoinDataVoNullable = ReceivedCoinDataVo(
                        name = getStringExtra(DetailFeatureReceiver.COIN_NAME_PARAM)
                            ?: DetailFeatureReceiver.UNKNOWN,
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
                    snackbarHostState = viewModel.snackbarHostState,
                    coinsNavigation = coinsNavigation,
                    favoritesNavigation = favoritesNavigation,
                    detailNavigation = detailNavigation,
                    showSnackbarMessage = viewModel::showSnackbarMessage,
                    recreateApplication = ::recreateApplication,
                    receivedCoinDataVoNullable = receivedCoinDataVoNullable
                )
            }
        }
    }

    private fun recreateApplication() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
        Runtime.getRuntime()
            .exit(0)
    }
}