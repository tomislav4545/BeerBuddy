package com.example.beerbuddy.ui.beerdetails.mapper

import com.example.beerbuddy.model.Beer
import com.example.beerbuddy.ui.beerdetails.BeerDetailsViewState

class BeerDetailsMapperImpl : BeerDetailsMapper {
    override fun toBeerDetailsViewState(beer: Beer): BeerDetailsViewState {
        return BeerDetailsViewState(
            id = beer.id,
            image = beer.image,
            name = beer.name,
            desc = beer.desc,
            isFavorite = beer.isFavorite
        )
    }
}
