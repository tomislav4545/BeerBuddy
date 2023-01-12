package com.example.beerbuddy.data.di

import com.example.beerbuddy.data.repository.BeerRepository
import com.example.beerbuddy.data.repository.BeerRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<BeerRepository> {
        BeerRepositoryImpl(
            beerService = get(),
            beerDao = get(),
            bgDispatcher = Dispatchers.IO
        )
    }
}
