package com.example.beerbuddy.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class BeerCardViewState(
    val image: String,
    val isFavorite: Boolean,
)

@Composable
fun BeerCard(
    modifier: Modifier,
    beerCardViewState: BeerCardViewState,
    onFavoriteButtonClicked: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 1.dp, horizontal = 1.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 15.dp
    ) {
        Box {
            AsyncImage(
                model = beerCardViewState.image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            FavoriteButton(
                isFavorite = beerCardViewState.isFavorite,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        top = 8.dp
                    )
                    .size(35.dp),
                onClick = { onFavoriteButtonClicked() }
            )
        }
    }
}

@Preview
@Composable
fun BeerCardPreview() {
    val beerCardViewState = BeerCardViewState(
        image = "https://images2.imgbox.com/c5/bc/mBKD88dp_o.jpg",
        isFavorite = true
    )
    BeerCard(
        modifier = Modifier.padding(5.dp),
        beerCardViewState = beerCardViewState,
        onFavoriteButtonClicked = {},
        onClick = {})
}
