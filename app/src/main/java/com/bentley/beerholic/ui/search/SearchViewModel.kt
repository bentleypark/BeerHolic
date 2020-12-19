package com.bentley.beerholic.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.bentley.beerholic.data.remote.SearchRepository
import com.bentley.beerholic.data.remote.response.BeerData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel @ViewModelInject
constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<BeerData>>? = null

    fun searchBeers() {
        viewModelScope.launch {
            val response = searchRepository.searchBeers("korea")
            Timber.d("response: $response")
        }
    }
}