package com.example.beerbuddy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbFavoriteBeer::class], version = 1)
abstract class BeerBuddyDatabase : RoomDatabase() {
    abstract fun favoriteBeerDao(): FavoriteBeerDao
}
