package com.maxkor.feature.coins.impl.domain.model

import kotlin.math.roundToInt
import kotlin.random.Random

data class Cryptocoin(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    var isFavorite: Boolean,
) {
    companion object {
        val testExemplar = createTestExemplar()
        val testExemplars = List(60) { createTestExemplar(id = it) }

        private fun createTestExemplar(
            id: Int = 0,
            name: String = "Name $id",
            price: String = "$${((Random.nextDouble() * 100).roundToInt()) / 100}",
            imageUrl: String = "https://cdn2.unrealengine.com/stalker-2-1-1920x1080-aeaa277bcf66.jpg",
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
