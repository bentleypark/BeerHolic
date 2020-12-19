package com.bentley.beerholic.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bentley.beerholic.data.remote.SearchRepository
import com.bentley.beerholic.data.remote.response.BeerData
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel @ViewModelInject
constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: List<BeerData> = emptyList()

    private var position = 1

    fun searchBeers() {
        viewModelScope.launch {
            val response = searchRepository.searchBeers("korea")
            Timber.d("response: $response")
//            if (response!!.isNotEmpty()) {
//                position += 1
//                fetchNextPage()
//            }
        }
    }

    fun fetchNextPage() {
        viewModelScope.launch {
            val response = searchRepository.searchBeers("korea", position)
            Timber.d("response: $response")
        }
    }
}