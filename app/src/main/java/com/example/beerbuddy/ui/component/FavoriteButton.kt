package com.example.beerbuddy.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.beerbuddy.R

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outlined),
        colorFilter = ColorFilter.tint(Black),
        contentDescription = null,
        modifier = modifier
            .size(dimensionResource(id = R.dimen.favorite_button_size))
            .clip(CircleShape)
            .clickable { onClick() }
    )
}

@Preview
@Composable
private fun FavoriteButtonPreview() {
    FavoriteButton(isFavorite = true, onClick = {}, modifier = Modifier)
}
