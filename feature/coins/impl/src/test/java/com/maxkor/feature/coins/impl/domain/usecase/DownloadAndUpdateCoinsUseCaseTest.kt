package com.maxkor.feature.coins.impl.domain.usecase

import android.util.Log
import com.maxkor.core.base.domain.repository.CheckerRepository
import com.maxkor.feature.coins.impl.domain.model.parameters.DownloadAndUpdateCoinsParams
import com.maxkor.feature.coins.impl.domain.repository.CoinsRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test

class DownloadAndUpdateCoinsUseCaseTest {

    private lateinit var coinsRepository: CoinsRepository
    private lateinit var checkerRepository: CheckerRepository
    private lateinit var useCase: DownloadAndUpdateCoinsUseCase

    @Before
    fun setUp() {
        coinsRepository = mockk<CoinsRepository>()
        checkerRepository = mockk<CheckerRepository>()
        useCase = DownloadAndUpdateCoinsUseCase(
            dispatcherIo = Dispatchers.IO,
            coinsRepository = coinsRepository,
            checkerRepository = checkerRepository
        )
    }

    @Test
    fun `successfully downloads and updates coins with internet`() = runBlocking {
        // Arrange
        val informUserOnFailure: (String) -> Unit = mockk(relaxed = true)
        coEvery { checkerRepository.hasInternetConnection() } returns true
        coEvery { coinsRepository.downloadAndUpdateCoins(any(), any()) } just Runs

        // Act
        useCase.invoke(DownloadAndUpdateCoinsParams(informUserOnFailure))

        // Assert
        coVerify {
            coinsRepository.downloadAndUpdateCoins(
                true,
                informUserOnFailure
            )
        }
    }

    @Test
    fun `handles no internet connection`() {
        // Arrange
        val informUserOnFailure: (String) -> Unit = mockk(relaxed = true)
        every { checkerRepository.hasInternetConnection() } returns false

        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0

        // Act
        runBlocking { useCase.invoke(DownloadAndUpdateCoinsParams(informUserOnFailure)) }

        // Assert
//        coVerify(exactly = 1) { coinsRepository.downloadAndUpdateCoins(any(), any()) }
        verify(exactly = 1) { informUserOnFailure(any()) }
    }
}