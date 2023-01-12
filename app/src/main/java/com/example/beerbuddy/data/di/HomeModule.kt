package com.example.beerbuddy.data.di

import com.example.beerbuddy.ui.home.HomeViewModel
import com.example.beerbuddy.ui.home.mapper.HomeScreenMapper
import com.example.beerbuddy.ui.home.mapper.HomeScreenMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(
            beerRepository = get(),
            homeScreenMapper = get()
        )
    }
    single<HomeScreenMapper> { HomeScreenMapperImpl() }
}
