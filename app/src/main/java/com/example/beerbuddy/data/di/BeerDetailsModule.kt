package com.example.beerbuddy.data.di

import com.example.beerbuddy.ui.beerdetails.BeerDetailsViewModel
import com.example.beerbuddy.ui.beerdetails.mapper.BeerDetailsMapper
import com.example.beerbuddy.ui.beerdetails.mapper.BeerDetailsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val beerDetailsModule = module {
    viewModel { (id: Int) ->
        BeerDetailsViewModel(
            id = id,
            beerRepository = get(),
            beerDetailsScreenMapper = get(),
        )
    }
    single<BeerDetailsMapper> { BeerDetailsMapperImpl() }
}
