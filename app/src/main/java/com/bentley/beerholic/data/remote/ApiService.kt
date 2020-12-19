package com.bentley.beerholic.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }

    @GET("beers")
    suspend fun search(
        @Query("beer_name") name: String,
        @Query("ids") ids: String,
        @Query("per_page") page: Int,
    )
}