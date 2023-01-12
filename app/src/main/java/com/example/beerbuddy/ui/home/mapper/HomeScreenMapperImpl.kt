package com.example.beerbuddy.ui.home.mapper

import com.example.beerbuddy.model.Beer
import com.example.beerbuddy.ui.component.BeerCardViewState
import com.example.beerbuddy.ui.home.HomeBeerViewState
import com.example.beerbuddy.ui.home.HomeViewState

class HomeScreenMapperImpl : HomeScreenMapper {

    override fun toHomeViewState(
        beers: List<Beer>
    ): HomeViewState {
        val homeBeerViewState = beers.map {
            HomeBeerViewState(
                id = it.id,
                BeerCardViewState(
                    image = it.image,
                    isFavorite = it.isFavorite
                )
            )
        }
        return HomeViewState(homeBeerViewState)
    }
}

