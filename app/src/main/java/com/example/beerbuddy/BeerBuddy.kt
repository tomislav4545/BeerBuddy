package com.example.beerbuddy

import android.app.Application
import android.util.Log
import com.example.beerbuddy.data.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BeerBuddy : Application() {
    override fun onCreate() {
        super.onCreate()

        Log.d("BeerBuddy", "App started")
        startKoin {
            androidLogger(org.koin.core.logger.Level.ERROR)
            androidContext(this@BeerBuddy)
            modules(
                networkModule,
                databaseModule,
                dataModule,
                favoritesModule,
                beerDetailsModule,
                homeModule
            )
        }
    }
}
