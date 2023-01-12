package com.example.beerbuddy.ui.home

import com.example.beerbuddy.ui.component.BeerCardViewState

data class HomeViewState(
    val beers: List<HomeBeerViewState>,
)

data class HomeBeerViewState(
    val id: Int,
    val beer: BeerCardViewState
)
