package com.example.beerbuddy.data.repository

import com.example.beerbuddy.model.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {

    fun favoriteBeer(): Flow<List<Beer>>

    fun fetchAllBeer(): Flow<List<Beer>>

    fun searchBeer(name : String): Flow<List<Beer>>

    fun beerDetails(id: Int): Flow<Beer>

    suspend fun addBeerToFavorites(id: Int)

    suspend fun removeBeerFromFavorites(id: Int)

    suspend fun toggleFavorite(id: Int)

}
