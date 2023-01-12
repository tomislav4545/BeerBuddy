package com.example.beerbuddy.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbFavoriteBeer(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "image") val image: String
)
