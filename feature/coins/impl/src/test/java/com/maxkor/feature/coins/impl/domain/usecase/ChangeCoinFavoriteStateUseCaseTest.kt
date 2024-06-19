//package com.maxkor.feature.coins.impl.domain.usecase
//
//import android.content.Context
//import com.maxkor.feature.coins.impl.domain.model.Coin
//import com.maxkor.feature.coins.impl.domain.model.parameters.ChangeCoinFavoriteStateParams
//import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
//import io.mockk.MockKSettings.relaxed
//import io.mockk.coVerify
//import io.mockk.mockk
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.runBlocking
//import org.junit.Before
//import org.junit.Test
//
//class ChangeCoinFavoriteStateUseCaseTest {
//
//    private lateinit var coinsRepository: CoinsRepository
//    private lateinit var useCase: ChangeCoinFavoriteStateUseCase
//
//    @Before
//    fun setUp() {
//        coinsRepository = mockk<CoinsRepository>(relaxed = true)
//        useCase = ChangeCoinFavoriteStateUseCase(
//            dispatcherIo = Dispatchers.IO,
//            coinsRepository = coinsRepository
//        )
//    }
//
//    @Test
//    fun `successfully toggles favorite state of coin`() = runBlocking {
//        // Arrange
//        val coin = Coin.testExemplar.copy(isFavorite = false)
//        val params = ChangeCoinFavoriteStateParams(coin)
//
//        // Act
//        useCase(params)
//
//        // Assert
//        coVerify { coinsRepository.updateCoin(coin.copy(isFavorite = true)) }
//    }
//}