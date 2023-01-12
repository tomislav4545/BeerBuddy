package com.example.beerbuddy.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerbuddy.data.repository.BeerRepository
import com.example.beerbuddy.ui.favorites.mapper.FavoritesMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val beerRepository: BeerRepository,
    favoritesMapper: FavoritesMapper,
) : ViewModel() {
    val favoritesViewState: StateFlow<FavoritesViewState> = beerRepository.favoriteBeer()
        .map { beers -> favoritesMapper.toFavoritesViewState(beers) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            FavoritesViewState(emptyList())
        )

    fun removeBeerFromFavorites(id: Int) {
        viewModelScope.launch {
            beerRepository.removeBeerFromFavorites(id)
        }
    }
}
