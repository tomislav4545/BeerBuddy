package com.example.beerbuddy.ui.favorites.mapper

import com.example.beerbuddy.model.Beer
import com.example.beerbuddy.ui.component.BeerCardViewState
import com.example.beerbuddy.ui.favorites.FavoritesBeerViewState
import com.example.beerbuddy.ui.favorites.FavoritesViewState

class FavoritesMapperImpl : FavoritesMapper {
    override fun toFavoritesViewState(favoriteBeers: List<Beer>): FavoritesViewState {

        val favoriteBeersViewStates = favoriteBeers.map {
            mapBeer(it)
        }

        return FavoritesViewState(favoriteBeersViewStates)
    }

    private fun mapBeer(beer: Beer): FavoritesBeerViewState {
        return FavoritesBeerViewState(
            id = beer.id,
            beerCardViewState = BeerCardViewState(
                image = beer.image,
                isFavorite = beer.isFavorite
            )
        )
    }
}
