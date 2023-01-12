package com.example.beerbuddy.data.di

import androidx.room.Room
import com.example.beerbuddy.data.database.BeerBuddyDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val APP_DATABASE_NAME = "app_database.db"
val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            BeerBuddyDatabase::class.java,
            APP_DATABASE_NAME,
        ).build()
    }
    single { get<BeerBuddyDatabase>().favoriteBeerDao() }
}
