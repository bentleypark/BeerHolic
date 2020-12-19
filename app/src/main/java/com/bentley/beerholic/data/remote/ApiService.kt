package com.bentley.beerholic.data.remote

import com.bentley.beerholic.data.remote.response.BeerData
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
//        @Query("ids") ids: String,
        @Query("page") page: Int,
    ): Response<List<BeerData>>

    suspend fun searchByid(
        @Query("ids") ids: String,
//        @Query("per_page") page: Int,
    ): Response<BeerData>
}