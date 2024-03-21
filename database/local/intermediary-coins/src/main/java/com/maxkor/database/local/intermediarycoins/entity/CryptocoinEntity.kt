package com.maxkor.database.local.intermediarycoins.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cryptocoins")
data class CryptocoinEntity(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,
    @PrimaryKey
    val id: Int,
)
