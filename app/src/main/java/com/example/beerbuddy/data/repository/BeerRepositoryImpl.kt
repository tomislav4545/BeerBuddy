package com.example.beerbuddy.data.repository

import com.example.beerbuddy.data.database.DbFavoriteBeer
import com.example.beerbuddy.data.database.FavoriteBeerDao
import com.example.beerbuddy.data.network.BeerService
import com.example.beerbuddy.data.network.model.BeerResponse
import com.example.beerbuddy.model.Beer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class BeerRepositoryImpl(
    private val beerService: BeerService,
    private val beerDao: FavoriteBeerDao,
    private val bgDispatcher: CoroutineDispatcher,
) : BeerRepository {

    private val beers: Flow<List<Beer>> =
        flow {
            val beerResponse: BeerResponse = beerService.fetchBeer()
            emit(beerResponse.beers)

        }.flatMapLatest { apiBeers ->
            beerDao.favorites().map { favoriteBeers ->
                apiBeers.map { apiBeer ->
                    apiBeer.toBeer(isFavorite = favoriteBeers.any { it.id == apiBeer.id })
                }
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )


    private val favorites = beerDao.favorites().mapLatest { dbFavoriteBeer ->
        dbFavoriteBeer.map { dbFavoriteBeer ->
            Beer(
                id = dbFavoriteBeer.id,
                image = dbFavoriteBeer.image,
                name = "",
                desc = "",
                isFavorite = true,
            )
        }
    }.flowOn(bgDispatcher)

    override fun favoriteBeer(): Flow<List<Beer>> = favorites

    private suspend fun findBeer(id: Int): Beer? {
        val beers = beers.first()
        for (beer in beers) {
            if (beer.id == id) {
                return beer
            }
        }
        return null
    }

    override suspend fun addBeerToFavorites(id: Int) {
        val beer = findBeer(id)
        val dbFavoriteBeer = DbFavoriteBeer(id = id, image = beer?.image ?: "")
        runBlocking(bgDispatcher) {
            beerDao.insertBeer(dbFavoriteBeer)
        }
    }

    override suspend fun removeBeerFromFavorites(id: Int) {
        runBlocking(bgDispatcher) {
            beerDao.deleteBeer(id)
        }
    }

    override suspend fun toggleFavorite(id: Int) {
        val listOfFavorites = beerDao.favorites().first()
        val isFavorite = listOfFavorites.any { it.id == id }
        if (isFavorite) {
            removeBeerFromFavorites(id)
        } else {
            addBeerToFavorites(id)
        }
    }

    override fun beerDetails(id: Int): Flow<Beer> = flow {
        emit(beerService.fetchBeerById(id))
    }.flatMapLatest { response ->
        beerDao.favorites().map { favoriteBeers ->
            response.beers.map { beer ->
                beer.toBeer(isFavorite = favoriteBeers.any { it.id == beer.id })
            }
        }
    }.flatMapConcat { list -> list.asFlow() }

    override fun searchBeer(name: String): Flow<List<Beer>> = flow {
        emit(beerService.fetchBeerByName(name))
    }.flatMapLatest { response ->
        beerDao.favorites().map { favoriteBeers ->
            response.beers.map { beer -> beer.toBeer(isFavorite = favoriteBeers.any { it.id == beer.id }) }
        }
    }

    override fun fetchAllBeer(): Flow<List<Beer>> = flow {
        emit(beerService.fetchBeer())
    }.flatMapLatest { response ->
        beerDao.favorites().map { favoriteBeers ->
            response.beers.map { beer -> beer.toBeer(isFavorite = favoriteBeers.any { it.id == beer.id }) }
        }
    }
}
