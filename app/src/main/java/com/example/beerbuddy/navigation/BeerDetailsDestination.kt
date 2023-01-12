package com.example.beerbuddy.navigation

const val BEER_DETAILS_ROUTE = "BeerDetails"
const val BEER_ID_KEY = "id"
const val BEER_DETAILS_ROUTE_WITH_PARAMS = "$BEER_DETAILS_ROUTE/{$BEER_ID_KEY}"

object BeerDetailsDestination : BeerBuddyDestination(BEER_DETAILS_ROUTE_WITH_PARAMS){
    fun createNavigationRoute(id: Int): String = "$BEER_DETAILS_ROUTE/$id"
}
