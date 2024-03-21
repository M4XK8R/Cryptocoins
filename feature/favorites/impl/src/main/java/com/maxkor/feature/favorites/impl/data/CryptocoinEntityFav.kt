package com.maxkor.feature.favorites.impl.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cryptocoins_fav")
data class CryptocoinEntityFav(
//    @ColumnInfo(name = "name")
    val name: String,
//    @ColumnInfo(name = "price")
    val price: String,
//    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
//    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,
    @PrimaryKey
    val id: Int,
)
