package com.bentley.beerholic.data.remote

import androidx.paging.PagingSource
import com.bentley.beerholic.data.remote.response.BeerData
import timber.log.Timber

class SearchPagingSource constructor(
    private val apiService: ApiService,
    private val query: String,
) :
    PagingSource<Int, BeerData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerData> {
        return try {
            val nextPageNumber = params.key ?: 1

            val response = apiService.searchByName(
                query,
                nextPageNumber
            )

            LoadResult.Page(
                data = response.body()!!,
                prevKey = null,
                nextKey = if (response.body()!!.isNotEmpty()) {
                    nextPageNumber + 1
                } else {
                    nextPageNumber
                }
            )
        } catch (e: Exception) {
            Timber.e(e)
            return LoadResult.Error(e)
        }
    }
}