package com.bentley.beerholic.data.remote.response

data class BeerModel (
    val id: Int,
    val name: String,
    val abv: Float,
    val first_brewed: String,
    val description: String,
    val image_url: String,
)
