package com.bentley.beerholic.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bentley.beerholic.data.remote.response.BeerData
import com.bentley.beerholic.ui.base.BaseRepository
import kotlinx.coroutines.flow.Flow

class SearchRepository constructor(
    private val apiService: ApiService
) : BaseRepository() {

    fun searchBeers(query: String, pageSize: Int): Flow<PagingData<BeerData>> {
        return Pager(PagingConfig(pageSize)) {
            SearchPagingSource(apiService, query)
        }.flow
    }
}