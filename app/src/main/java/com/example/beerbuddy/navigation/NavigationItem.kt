package com.example.beerbuddy.navigation

import com.example.beerbuddy.R

const val HOME_ROUTE = "Home"
const val FAVORITES_ROUTE = "Favorites"

sealed class NavigationItem(
    override val route: String,
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val labelId: Int,
) : BeerBuddyDestination(route) {
    object HomeDestination : NavigationItem(
        route = HOME_ROUTE,
        selectedIconId = R.drawable.ic_home_filled,
        unselectedIconId = R.drawable.ic_home_outlined,
        labelId = R.string.home,
    )

    object FavoritesDestination : NavigationItem(
        route = FAVORITES_ROUTE,
        selectedIconId = R.drawable.ic_favorites_list_filled,
        unselectedIconId = R.drawable.ic_favorites_list_outlined,
        labelId = R.string.favorites,
    )
}
