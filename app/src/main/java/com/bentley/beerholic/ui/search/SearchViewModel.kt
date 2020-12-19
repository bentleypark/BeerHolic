package com.bentley.beerholic.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bentley.beerholic.data.remote.SearchRepository
import com.bentley.beerholic.data.remote.response.BeerModel
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject
constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    private var currentQueryValue = ""

    private val _searchResult = MutableLiveData<List<BeerModel>>()
    val searchResult: LiveData<List<BeerModel>>
        get() = _searchResult

    private val _nextResult = MutableLiveData<List<BeerModel>>()
    val nextResult: LiveData<List<BeerModel>>
        get() = _nextResult

    private var position = 1

    fun searchBeers(query: String) {
        checkQueryIsChanged(query)
        viewModelScope.launch {
            val response = searchRepository.searchBeers(currentQueryValue)
            _searchResult.value = response

            if (response != null && response.isNotEmpty()) {
                position += 1
            }
        }
    }

    private fun checkQueryIsChanged(newQuery: String) {
        if (currentQueryValue.isEmpty()) {
            currentQueryValue = newQuery
        }

        if (currentQueryValue != newQuery) {
            currentQueryValue = newQuery
            position = 1
        }
    }

    fun fetchNextPage() {
        viewModelScope.launch {
            val response = searchRepository.searchBeers(currentQueryValue, position)
            _nextResult.value = response
            if (response!!.isNotEmpty()) {
                position += 1
            }
        }
    }
}