package com.maxkor.feature.coins.impl.domain.model

import kotlin.math.roundToInt
import kotlin.random.Random

data class Coin(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    val isFavorite: Boolean,
) {
    companion object {
        val testExemplar = createTestExemplar()
        val testExemplars = List(60) { createTestExemplar(id = it) }

        private fun createTestExemplar(
            id: Int = 0,
            name: String = "Name $id",
            price: String = "${((Random.nextDouble() * 10000).roundToInt()) / 100.0} $",
            imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Bitcoin.svg/1200px-Bitcoin.svg.png",
            isFavorite: Boolean = Random.nextBoolean(),
        ) = Coin(
            id = id,
            name = name,
            price = price,
            imageUrl = imageUrl,
            isFavorite = isFavorite
        )
    }
}
