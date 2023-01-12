package com.example.beerbuddy.ui.beerdetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.beerbuddy.model.Beer
import com.example.beerbuddy.ui.beerdetails.mapper.BeerDetailsMapper
import com.example.beerbuddy.ui.beerdetails.mapper.BeerDetailsMapperImpl
import com.example.beerbuddy.ui.component.FavoriteButton

private val beer = Beer(
    image = "https://images2.imgbox.com/c5/bc/mBKD88dp_o.jpg",
    name = "Pan Lager",
    desc ="O'Hara Irish Stout je robusna okusa, " +
            "upotpunjene punoće i glatkog je osjećaja u ustima. " +
            "Velikodušni dodatak Fuggle hmelja posuđuje oporu gorčinu na suhom poput " +
            "espressa završetku. Ovo stout pivo je ispunjeno bogatim složenim aromama kave pomiješano" +
            " s lakim notama slatkog korijena. Kombinacija tradicionalnog Stout hmelja s pomoćnom trunkom pečena ječma omogućuje" +
            " nam da ostanemo vjerni irskoj tradiciji, okus za kojim često čeznu stout pivopije.",
    id = 5,
    isFavorite = true
)
private val BeerDetailsMapper: BeerDetailsMapper = BeerDetailsMapperImpl()
val beerDetailsViewState = BeerDetailsMapper.toBeerDetailsViewState(beer)

@Composable
fun BeerDetailsRoute(
    viewModel: BeerDetailsViewModel,
    modifier: Modifier = Modifier
) {
    val beerDetailsViewState: BeerDetailsViewState by viewModel.beerDetailsViewModel.collectAsState()

    BeerDetailsScreen(
        beerDetailsViewState = beerDetailsViewState,
        onAddToFavorites = {
            viewModel.toggleFavorite(beerDetailsViewState.id)
        },
        modifier = modifier
    )
}

@Composable
fun BeerDetailsScreen(
    modifier: Modifier,
    beerDetailsViewState: BeerDetailsViewState,
    onAddToFavorites: () -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            AsyncImage(
                model = beerDetailsViewState.image,
                contentDescription = null,
                modifier = Modifier.height(400.dp)
            )
            FavoriteButton(
                isFavorite = beerDetailsViewState.isFavorite,
                onClick = onAddToFavorites
            )
        }
        Text(
            text = beerDetailsViewState.name,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(25.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = beerDetailsViewState.desc,
            style = MaterialTheme.typography.body2,
            fontSize = 20.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}

@Preview
@Composable
private fun BeerDetailsScreenPreview() {
    BeerDetailsScreen(modifier = Modifier.padding(15.dp),
        beerDetailsViewState = beerDetailsViewState,
        onAddToFavorites = {})
}
