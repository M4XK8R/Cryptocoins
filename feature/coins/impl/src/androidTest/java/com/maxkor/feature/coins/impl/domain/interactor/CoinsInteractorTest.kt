//package com.maxkor.feature.coins.impl.domain.interactor
//
//import android.content.Context
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.maxkor.core.base.domain.repository.CheckerRepository
//import com.maxkor.feature.coins.impl.data.local.dao.CoinsDao
//import com.maxkor.feature.coins.impl.data.local.db.CoinsDatabase
//import com.maxkor.feature.coins.impl.data.local.mapper.toCoinEntity
//import com.maxkor.feature.coins.impl.data.remote.api.CoinsApi
//import com.maxkor.feature.coins.impl.data.repository.CoinsRepositoryImpl
//import com.maxkor.feature.coins.impl.domain.interactor.impl.CoinsInteractorImpl
//import com.maxkor.feature.coins.impl.domain.model.Coin
//import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.runBlocking
//import org.hamcrest.CoreMatchers
//import org.hamcrest.MatcherAssert.assertThat
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mockito
//import java.io.IOException
//import kotlin.random.Random
//
//@RunWith(AndroidJUnit4::class)
//class CoinsInteractorTest {
//    private lateinit var coinsDao: CoinsDao
//    private lateinit var coinsDatabase: CoinsDatabase
//    private lateinit var coinsRepository: CoinsRepository
//    private lateinit var coinsInteractor: CoinsInteractor
//
//    private val context = ApplicationProvider.getApplicationContext<Context>()
//
//    @Before
//    fun setUpTest() {
//        initDatabaseAndDao()
//        initRepository()
//        initInteractor()
//        loadDataToDatabase()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun tearDownTest() {
//        closeDatabase()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun the_coin_favState_changed() = runBlocking {
//        // Arrange
//        val testCoinsSize: Int = Coin.testExemplars.size
//        val testCoinId = Random.nextInt(testCoinsSize)
//        val testCoin = Coin.createTestExemplar(id = testCoinId)
//        val favoriteStateBeforeAct = testCoin.isFavorite
//
//        // Act
//        coinsInteractor.changeCoinFavoriteState(testCoin)
//
//        // Assert
//        val changedCoin = coinsDao.getAll()
//            .find { it.id == testCoin.id }
//        assertThat(
//            changedCoin?.isFavorite,
//            CoreMatchers.equalTo(!favoriteStateBeforeAct)
//        )
//    }
//
//    /**
//     * Private sector
//     */
//    // Before
//    private fun initDatabaseAndDao() {
//        coinsDatabase = Room.inMemoryDatabaseBuilder(
//            context,
//            CoinsDatabase::class.java
//        ).build()
//        coinsDao = coinsDatabase.coinsDao()
//    }
//
//    // Before
//    private fun initRepository() {
//        coinsRepository = CoinsRepositoryImpl(
//            dao = coinsDao,
//            api = Mockito.mock(CoinsApi::class.java),
//            context = context
//        )
//    }
//
//    // Before
//    private fun initInteractor() {
//        coinsInteractor = CoinsInteractorImpl(
//            context = context,
//            dispatcherIo = Dispatchers.IO,
//            coinsRepository = coinsRepository,
//            checkerRepository = Mockito.mock(CheckerRepository::class.java)
//        )
//    }
//
//    // Before
//    private fun loadDataToDatabase() = runBlocking {
//        val coinsEntities = Coin.testExemplars
//            .map { it.toCoinEntity() }
//        coinsDao.update(coinsEntities)
//    }
//
//    // After
//    private fun closeDatabase() {
//       coinsDatabase.close()
//    }
//}