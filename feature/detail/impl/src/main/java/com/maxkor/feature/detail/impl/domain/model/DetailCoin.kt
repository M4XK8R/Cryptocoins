package com.maxkor.feature.detail.impl.domain.model

import kotlin.math.roundToInt
import kotlin.random.Random

data class DetailCoin(
    val name: String,
    val price: String,
    val imageUrl: String,
    val extraInfo: ExtraCoinInfo,
) {
    companion object {
        val testExemplar = createTestExemplar()

        private fun createTestExemplar(
            name: String = "Name",
            price: String = "${((Random.nextDouble() * 10000).roundToInt()) / 100.0}$",
            imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitcoin.svg/1200px-Bitcoin.svg.png",
            extraInfo: String = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"",
        ) = DetailCoin(
            name = name,
            price = price,
            imageUrl = imageUrl,
            extraInfo = ExtraCoinInfo(value = extraInfo)
        )
    }
}
