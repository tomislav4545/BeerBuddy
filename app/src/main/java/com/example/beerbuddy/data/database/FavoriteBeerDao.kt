package com.example.beerbuddy.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBeerDao {
    @Query("SELECT * FROM DbFavoriteBeer")
    fun favorites(): Flow<List<DbFavoriteBeer>>

    @Query("DELETE FROM DbFavoriteBeer WHERE id = :id")
    fun deleteBeer(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(dbFavoriteMovie: DbFavoriteBeer)
}
