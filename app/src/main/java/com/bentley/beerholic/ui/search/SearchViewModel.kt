package com.bentley.beerholic.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bentley.beerholic.data.remote.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject
constructor(private val searchRepository: SearchRepository) : ViewModel() {

    fun searchBeers() {
        viewModelScope.launch{
            searchRepository.searchBeers()
        }
    }
}