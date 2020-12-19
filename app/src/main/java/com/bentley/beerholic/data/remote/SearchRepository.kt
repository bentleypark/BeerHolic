package com.bentley.beerholic.data.remote

import com.bentley.beerholic.ui.base.BaseRepository

class SearchRepository constructor(
    private val apiService: ApiService
) : BaseRepository() {

    suspend fun getBeers() =
        safeApiCall(call = {
            apiService.searchByName("korea",1)
        }, errorMessage = "Error!")
}