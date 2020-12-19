package com.bentley.beerholic.data.remote

import com.bentley.beerholic.data.remote.response.BeerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }

    @GET("beers")
    suspend fun searchByName(
        @Query("beer_name") name: String,
        @Query("page") page: Int,
        @Query("per_page") loadSize: Int,
    ): Response<List<BeerModel>>

    @GET("beers")
    suspend fun searchById(
        @Query("ids") ids: String,
    ): Response<List<BeerModel>>
}