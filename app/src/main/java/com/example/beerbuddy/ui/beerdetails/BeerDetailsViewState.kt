package com.example.beerbuddy.ui.beerdetails

data class BeerDetailsViewState(
    val id: Int,
    val image: String,
    val name: String,
    val desc: String,
    val isFavorite: Boolean
) {
    companion object EMPTY {
        fun getEmptyObject(): BeerDetailsViewState {
            return BeerDetailsViewState(
                id = 0,
                image = "",
                name = "",
                desc = "",
                isFavorite = false
            )
        }
    }
}
