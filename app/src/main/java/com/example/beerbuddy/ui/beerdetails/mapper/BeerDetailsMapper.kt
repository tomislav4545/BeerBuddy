package com.example.beerbuddy.ui.beerdetails.mapper

import com.example.beerbuddy.model.Beer
import com.example.beerbuddy.ui.beerdetails.BeerDetailsViewState

interface BeerDetailsMapper {
    fun toBeerDetailsViewState(beer: Beer): BeerDetailsViewState
}
