package com.example.beerbuddy.ui.home.mapper

import com.example.beerbuddy.model.Beer
import com.example.beerbuddy.ui.home.HomeViewState

interface HomeScreenMapper {
    fun toHomeViewState(
        beer: List<Beer>,
    ): HomeViewState
}
