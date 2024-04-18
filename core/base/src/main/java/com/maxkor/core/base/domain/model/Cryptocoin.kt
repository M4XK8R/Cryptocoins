package com.maxkor.core.base.domain.model

import kotlin.math.roundToInt
import kotlin.random.Random

data class Cryptocoin(
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
            price: String = "${((Random.nextDouble() * 10000).roundToInt()) / 100.0}$",
            imageUrl: String = "https://cryptologos.cc/logos/bitcoin-btc-logo.svg",
            isFavorite: Boolean = Random.nextBoolean(),
        ) = Cryptocoin(
            id = id,
            name = name,
            price = price,
            imageUrl = imageUrl,
            isFavorite = isFavorite
        )
    }
}
