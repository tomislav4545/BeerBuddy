package com.example.beerbuddy.ui.favorites.mapper

import com.example.beerbuddy.model.Beer
import com.example.beerbuddy.ui.favorites.FavoritesViewState

interface FavoritesMapper {
    fun toFavoritesViewState(favoriteMovies: List<Beer>): FavoritesViewState
}
