package com.example.beerbuddy.data.network

import com.example.beerbuddy.data.network.model.BeerResponse

interface BeerService {

    suspend fun fetchBeerByName(name: String): BeerResponse

    suspend fun fetchBeer(): BeerResponse

    suspend fun fetchBeerById(id: Int): BeerResponse

}
