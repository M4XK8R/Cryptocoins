//package com.maxkor.feature.coins.impl.domain.interactor
//
//import android.content.Context
//import com.maxkor.core.base.domain.repository.CheckerRepository
//import com.maxkor.feature.coins.impl.domain.interactor.impl.CoinsInteractorImpl
//import com.maxkor.feature.coins.impl.domain.model.Coin
//import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
//import io.mockk.Runs
//import io.mockk.coEvery
//import io.mockk.coVerify
//import io.mockk.every
//import io.mockk.just
//import io.mockk.mockk
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//
//class CoinsInteractorTest {
//
//    private lateinit var coinsRepository: CoinsRepository
//    private lateinit var checkerRepository: CheckerRepository
//    private lateinit var coinsInteractor: CoinsInteractor
//
//    @Before
//    fun setUp() {
//        coinsRepository = mockk<CoinsRepository>()
//        checkerRepository = mockk<CheckerRepository>()
//        val context = mockk<Context>()
//        coinsInteractor = CoinsInteractorImpl(
//            context = context,
//            dispatcherIo = Dispatchers.IO,
//            coinsRepository = coinsRepository,
//            checkerRepository = checkerRepository
//        )
//    }
//
//    // getCoinsFlow returns a flow of coins from CoinsRepository
//    @Test
//    fun test_getCoinsFlow_returnsFlowOfCoins() {
//        val coinsFlow = flowOf(Coin.testExemplars)
//        every { coinsRepository.getCoinsFlow() } returns coinsFlow
//
//        // Call the function under test
//        val result = coinsInteractor.getCoinsFlow()
//
//        // Assert the result
//        assertEquals(coinsFlow, result)
//    }
//
//    // getCoinsFlow returns an empty flow when CoinsRepository returns empty list
//    @Test
//    fun test_getCoinsFlow_returnsEmptyFlowWhenRepositoryReturnsEmptyList() {
//        val coinsFlow = flowOf(emptyList<Coin>())
//        every { coinsRepository.getCoinsFlow() } returns coinsFlow
//
//        // Call the function under test
//        val result = coinsInteractor.getCoinsFlow()
//
//        // Assert the result
//        assertEquals(coinsFlow, result)
//    }
//
//    // changing the favorite state of a coin updates the coin in the repository
//    @Test
//    fun test_change_coin_favorite_state_updates_coin_in_repository() {
//        // Create test data
//        val coin = Coin.testExemplar
//
//        // Mock behavior of coinsRepository.updateCoin()
//        coEvery { coinsRepository.updateCoin(any()) } just Runs
//
//        // Call the function under test
//        runBlocking { coinsInteractor.changeCoinFavoriteState(coin) }
//
//        // Verify that coinsRepository.updateCoin() was called with the updated coin
//        coVerify {
//            coinsRepository.updateCoin(
//                coin.copy(
//                    isFavorite = !coin.isFavorite
//                )
//            )
//        }
//    }
//
//    // updating data with internet connection and empty new coins list
//    // does not update the coins in the repository
//    @Test
//    fun test_update_data_with_empty_new_coins_list_does_not_update_coins_in_repository() {
//        // Mock behavior of checkerRepository.hasInternetConnection()
//        every { checkerRepository.hasInternetConnection() } returns true
//
//        // Mock behavior of coinsRepository.getCoinsFromServer()
//        coEvery { coinsRepository.getCoinsFromServer(any()) } returns emptyList()
//
//        // Mock behavior of coinsRepository.getCoins()
//        coEvery { coinsRepository.getCoins() } returns emptyList()
//
//        // Mock behavior of coinsRepository.updateCoins()
//        coEvery { coinsRepository.updateCoins(any()) } just Runs
//
//        // Call the function under test
//        runBlocking { coinsInteractor.updateCoins { } }
//
//        // Verify that coinsRepository.updateCoins() was not called
//        coVerify(exactly = 0) { coinsRepository.updateCoins(any()) }
//    }
//
//    // Empty list of coins returned from server
//    @Test
//    fun test_updateData_emptyCoins() = runTest {
//        every { checkerRepository.hasInternetConnection() } returns true
//        coEvery { coinsRepository.getCoinsFromServer(any()) } returns emptyList()
//        coEvery { coinsRepository.getCoins() } returns emptyList()
//
//        // Call the function under test
//        coinsInteractor.updateCoins { }
//
//        // Verify that the updateCoins function is not called
//        coVerify(exactly = 0) { coinsRepository.updateCoins(any()) }
//    }
//}