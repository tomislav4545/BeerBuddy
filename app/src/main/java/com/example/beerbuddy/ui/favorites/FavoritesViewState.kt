package com.example.beerbuddy.ui.favorites

import com.example.beerbuddy.ui.component.BeerCardViewState

data class FavoritesBeerViewState(
    val id: Int,
    val beerCardViewState: BeerCardViewState
)

data class FavoritesViewState(
    val favoriteBeersViewStates: List<FavoritesBeerViewState>
)
