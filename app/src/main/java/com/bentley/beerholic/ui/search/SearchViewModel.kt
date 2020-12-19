package com.bentley.beerholic.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bentley.beerholic.data.remote.SearchRepository
import com.bentley.beerholic.data.remote.response.BeerModel
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel @ViewModelInject
constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private var currentQueryValue: String? = null

    private val _searchResult = MutableLiveData<List<BeerModel>>()
    val searchResult: LiveData<List<BeerModel>>
        get() = _searchResult

    private var position = 1

    fun searchBeers(query: String) {
        viewModelScope.launch {
            val response = searchRepository.searchBeers(query)
            Timber.d("response: $response")
            if (response!!.isNotEmpty()) {
                _searchResult.value = response
                position += 1
            }
        }
    }

    fun fetchNextPage() {
        viewModelScope.launch {
            val response = searchRepository.searchBeers("korea", position)
            Timber.d("response: $response")
        }
    }
}