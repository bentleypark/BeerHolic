package com.bentley.beerholic.ui.search

import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bentley.beerholic.R
import com.bentley.beerholic.databinding.FragmentSearchBinding
import com.bentley.beerholic.ui.base.ViewBindingHolder
import com.bentley.beerholic.ui.base.ViewBindingHolderImpl
import com.bentley.beerholic.utils.hideKeyboard
import com.bentley.beerholic.utils.makeGone
import com.bentley.beerholic.utils.makeSnackBar
import com.bentley.beerholic.utils.makeVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(),
    ViewBindingHolder<FragmentSearchBinding> by ViewBindingHolderImpl() {

    private val viewModel: SearchViewModel by viewModels()
    private var searchJob: Job? = null
    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = initBinding(FragmentSearchBinding.inflate(layoutInflater), this) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        initRecycleView()
        setUpObserves()
    }

    private fun setUpViews() {
        binding!!.apply {

            etSearch.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        performSearch()
                    }
                    true
                }

                btnSearch.setOnClickListener {
                    performSearch()
                }
            }
        }
    }

    private fun initRecycleView() {
        searchResultAdapter = SearchResultAdapter(mutableListOf())
        binding!!.searchList.apply {
            adapter = searchResultAdapter
            setHasFixedSize(true)
        }
    }

    private fun setUpObserves() {
        viewModel.apply {
            searchResult.observe(viewLifecycleOwner, { result ->
                if (result.isNotEmpty()) {
                    searchResultAdapter.addAll(result)
                } else {
                    binding!!.tvNoResult.makeVisible()
                }
            })
        }
    }

    private fun performSearch() {
        val query = SpannableStringBuilder(binding!!.etSearch.text).toString().trim()
        if (query.isNotEmpty()) {
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {

                binding.apply {
                    ivDefaultImg.makeGone()
                    searchList.makeGone()
                    tvNoResult.makeGone()
                    progressCircular.makeVisible()
                    etSearch.apply {
                        clearFocus()
                        hideKeyboard()
                    }
                    delay(1000)
                    progressCircular.makeGone()
                    searchList.makeVisible()
                }

                viewModel.searchBeers(query)
            }
        } else {
            binding.searchLayout.makeSnackBar(getString(R.string.no_input_text_waring))
        }
    }
}