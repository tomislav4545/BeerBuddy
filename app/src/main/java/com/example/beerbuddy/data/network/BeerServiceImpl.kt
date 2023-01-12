package com.example.beerbuddy.data.network

import com.example.beerbuddy.data.network.model.BeerResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

private const val BASE_URL = "https://beerapi.glitch.me/beer"

class BeerServiceImpl(private val client: HttpClient) : BeerService {
    override suspend fun fetchBeerByName(name: String): BeerResponse {
        return client.get(
            "$BASE_URL?name_like=$name"
        ).body()
    }

    override suspend fun fetchBeerById(id: Int): BeerResponse {
        return client.get(
            "$BASE_URL?id=$id"
        ).body()
    }

    override suspend fun fetchBeer(): BeerResponse {
        return client.get(
            "$BASE_URL"
        ).body()
    }
}
