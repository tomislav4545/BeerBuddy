package com.example.beerbuddy.ui.beerdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerbuddy.data.repository.BeerRepository
import com.example.beerbuddy.ui.beerdetails.mapper.BeerDetailsMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BeerDetailsViewModel(
    private val id: Int,
    private val beerRepository: BeerRepository,
    private val beerDetailsScreenMapper: BeerDetailsMapper,
) : ViewModel() {

    val beerDetailsViewModel: StateFlow<BeerDetailsViewState> =
        beerRepository.beerDetails(id).map { beerDetails ->
            beerDetailsScreenMapper.toBeerDetailsViewState(beerDetails)
        }.stateIn(viewModelScope, SharingStarted.Lazily, BeerDetailsViewState.getEmptyObject())

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            beerRepository.toggleFavorite(id)
        }
    }
}
