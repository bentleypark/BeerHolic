package com.bentley.beerholic.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bentley.beerholic.data.remote.SearchRepository
import com.bentley.beerholic.data.remote.response.BeerModel
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailViewModel @ViewModelInject
constructor(private val searchRepository: SearchRepository) : ViewModel() {
    private val _result = MutableLiveData<BeerModel>()
    val result: LiveData<BeerModel>
        get() = _result

    fun fetchDetails(id: String) {
        viewModelScope.launch {
            val response = searchRepository.fetchBeerDetails(id)
            Timber.d("response: $response")
            _result.value = response!![0]
        }
    }
}