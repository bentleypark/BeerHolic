package com.bentley.beerholic.data.remote

import com.bentley.beerholic.data.remote.response.BeerData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }

    @GET("beers")
    suspend fun searchByName(
        @Query("name") name: String,
        @Query("page") page: Int,
        @Query("per_page") loadSize: Int,
    ): Response<List<BeerData>>

    suspend fun searchByid(
        @Query("ids") ids: String,
//        @Query("per_page") page: Int,
    ): Response<BeerData>
}