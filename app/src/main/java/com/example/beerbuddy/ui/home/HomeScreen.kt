package com.example.beerbuddy.ui.home

import com.example.beerbuddy.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beerbuddy.ui.component.BeerCard
import com.example.beerbuddy.ui.component.BeerCardViewState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.beerbuddy.model.Beer
import com.example.beerbuddy.ui.home.mapper.HomeScreenMapper
import com.example.beerbuddy.ui.home.mapper.HomeScreenMapperImpl

private val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()
private val beers: List<Beer> = listOf(
    Beer(
        id = 5,
        name = "Pan lager",
        desc = "",
        image = "https://images2.imgbox.com/bd/a1/Ak1qP2VD_o.jpg",
        isFavorite = true
    ),
    Beer(
        id = 45,
        name = "Pan lager zlatni",
        desc = "",
        image = "https://images2.imgbox.com/bd/a1/Ak1qP2VD_o.jpg",
        isFavorite = false
    ),
    Beer(
        id = 55,
        name = "Pan lager crveni",
        desc = "",
        image = "https://images2.imgbox.com/bd/a1/Ak1qP2VD_o.jpg",
        isFavorite = true
    ),
    Beer(
        id = 56,
        name = "Pan lager crni",
        desc = "",
        image = "https://images2.imgbox.com/bd/a1/Ak1qP2VD_o.jpg",
        isFavorite = false
    )
)

private val emptyBeer: List<Beer> = emptyList()

val homeViewState = homeScreenMapper.toHomeViewState(emptyBeer)

@Composable
fun HomeScreenRoute(
    viewModel: HomeViewModel,
    onNavigateToBeerDetails: (Int) -> Unit
) {
    val beers: HomeViewState by viewModel.beers.collectAsState()

    HomeScreen(
        onNavigateToBeerDetails = onNavigateToBeerDetails,
        onFavoriteButtonClicked = { viewModel.toggleFavorite(it) },
        viewState = beers,
        modifier = Modifier
    )
}

@Composable
private fun HomeScreen(
    onNavigateToBeerDetails: (Int) -> Unit,
    onFavoriteButtonClicked: (Int) -> Unit,
    viewState: HomeViewState,
    modifier: Modifier
) {
    if (viewState.beers.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.nije_pronadeno_pivo), fontSize = 30.sp)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = viewState.beers,
                key = { beer -> beer.id }) { beer ->
                BeerCard(
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp),
                    beerCardViewState = BeerCardViewState(
                        image = beer.beer.image,
                        isFavorite = beer.beer.isFavorite
                    ),
                    onFavoriteButtonClicked = { onFavoriteButtonClicked(beer.id) },
                    onClick = { onNavigateToBeerDetails(beer.id) }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNavigateToBeerDetails = {},
        onFavoriteButtonClicked = { },
        viewState = homeViewState,
        modifier = Modifier
    )
}
