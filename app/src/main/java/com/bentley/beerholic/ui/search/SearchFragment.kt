package com.bentley.beerholic.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bentley.beerholic.R
import com.bentley.beerholic.databinding.FragmentSearchBinding
import com.bentley.beerholic.ui.base.ViewBindingHolder
import com.bentley.beerholic.ui.base.ViewBindingHolderImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(),
    ViewBindingHolder<FragmentSearchBinding> by ViewBindingHolderImpl() {


    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = initBinding(FragmentSearchBinding.inflate(layoutInflater), this) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchBeers()
    }
}