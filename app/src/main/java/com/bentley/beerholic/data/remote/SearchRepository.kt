package com.bentley.beerholic.data.remote

import com.bentley.beerholic.ui.base.BaseRepository

class SearchRepository constructor(
    private val apiService: ApiService
) : BaseRepository() {

    suspend fun searchBeers(query: String, page: Int = 1) =
        safeApiCall(
            call = {
                apiService.searchByName(query, page, NETWORK_PAGE_SIZE)
            }, errorMessage = ""
        )

    companion object {
        private const val NETWORK_PAGE_SIZE = 30
    }
}