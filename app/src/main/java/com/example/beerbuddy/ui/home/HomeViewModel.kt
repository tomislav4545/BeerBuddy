package com.example.beerbuddy.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beerbuddy.data.repository.BeerRepository
import com.example.beerbuddy.ui.home.mapper.HomeScreenMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomeViewModel(
    private val beerRepository: BeerRepository,
    private val homeScreenMapper: HomeScreenMapper,
) : ViewModel(), KoinComponent {

    private val _query = MutableStateFlow("")
    val query = mutableStateOf("")

    private var _beers = _query.flatMapLatest { query ->
        beerRepository.searchBeer(query).map { beers ->
            homeScreenMapper.toHomeViewState(beer = beers)
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        HomeViewState(emptyList())
    )

    var beers: StateFlow<HomeViewState> = _beers

    fun setQuery(query: String) {
        this._query.value = query

        this.query.value = query
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            beerRepository.toggleFavorite(id)
        }
    }
}
