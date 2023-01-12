package com.example.beerbuddy.data.di

import com.example.beerbuddy.ui.favorites.FavoritesViewModel
import com.example.beerbuddy.ui.favorites.mapper.FavoritesMapper
import com.example.beerbuddy.ui.favorites.mapper.FavoritesMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel {
        FavoritesViewModel(
            beerRepository = get(),
            favoritesMapper = get()
        )
    }
    single<FavoritesMapper> { FavoritesMapperImpl() }
}
