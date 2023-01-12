package com.example.beerbuddy.data.network.model

import com.example.beerbuddy.model.Beer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeerResponse(
    @SerialName("beer")
    val beers: List<ApiBeer>,
)

@Serializable
data class ApiBeer(
    @SerialName("name")
    val name: String,
    @SerialName("desc")
    val desc: String,
    @SerialName("image")
    val image: String,
    @SerialName("id")
    val id: Int
) {
    fun toBeer(isFavorite: Boolean) = Beer(
        id = id,
        name = name,
        desc = desc,
        image = image,
        isFavorite = isFavorite
    )
}
