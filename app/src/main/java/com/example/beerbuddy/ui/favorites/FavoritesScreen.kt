package com.example.beerbuddy.ui.favorites

import com.example.beerbuddy.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beerbuddy.model.Beer
import com.example.beerbuddy.ui.component.BeerCard
import com.example.beerbuddy.ui.favorites.mapper.FavoritesMapper
import com.example.beerbuddy.ui.favorites.mapper.FavoritesMapperImpl
import androidx.compose.ui.res.stringResource


private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()
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

val favoritesViewState = favoritesMapper.toFavoritesViewState(beers)

@Composable
fun FavoritesRoute(
    viewModel: FavoritesViewModel,
    onNavigateToBeerDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val favoritesViewState: FavoritesViewState by viewModel.favoritesViewState.collectAsState()

    FavoritesScreen(
        favoritesViewState = favoritesViewState,
        onCardClick = onNavigateToBeerDetails,
        onFavoriteButtonClick = viewModel::removeBeerFromFavorites,
        modifier = modifier
    )
}

@Composable
fun FavoritesScreen(
    modifier: Modifier,
    favoritesViewState: FavoritesViewState,
    onCardClick: (Int) -> Unit,
    onFavoriteButtonClick: (Int) -> Unit
) {
    if (favoritesViewState.favoriteBeersViewStates.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.nema_piva), fontSize = 30.sp)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier
        ) {
            items(
                items = favoritesViewState.favoriteBeersViewStates,
                key = { movie -> movie.id }) { card ->
                BeerCard(
                    modifier = Modifier
                        .width(120.dp)
                        .height(180.dp),
                    beerCardViewState = card.beerCardViewState,
                    onClick = { onCardClick(card.id) },
                    onFavoriteButtonClicked = { onFavoriteButtonClick(card.id) }
                )
            }
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    val favoritesScreenModifier = Modifier
        .fillMaxSize()
        .padding(15.dp)

    FavoritesScreen(
        modifier = favoritesScreenModifier,
        favoritesViewState = favoritesViewState,
        onCardClick = { },
        onFavoriteButtonClick = { }
    )
}
