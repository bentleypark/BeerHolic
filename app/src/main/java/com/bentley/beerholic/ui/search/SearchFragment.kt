package com.bentley.beerholic.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bentley.beerholic.R
import com.bentley.beerholic.databinding.FragmentSearchBinding
import com.bentley.beerholic.ui.base.ViewBindingHolder
import com.bentley.beerholic.ui.base.ViewBindingHolderImpl
import com.bentley.beerholic.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(),
    ViewBindingHolder<FragmentSearchBinding> by ViewBindingHolderImpl() {

    private val viewModel: SearchViewModel by viewModels()
    private var searchJob: Job? = null
    private lateinit var searchResultAdapter: SearchResultAdapter
    private var isLastPage = false
    private var isLoading = false

    @Inject
    lateinit var pref: SharedPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = initBinding(FragmentSearchBinding.inflate(layoutInflater), this) {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    pref.setLastQuery("")
                    requireActivity().finish()
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkArgument()
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

                addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        searchResultAdapter.clearAll()
                        binding.ivDefaultImg.makeVisible()
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })
            }

            btnSearch.setOnClickListener {
                performSearch()
            }
        }
    }


    private fun initRecycleView() {
        searchResultAdapter = SearchResultAdapter(mutableListOf())
        binding!!.searchList.apply {
            adapter = searchResultAdapter
            setHasFixedSize(true)

            addOnScrollListener(object :
                PaginationScrollListener(this.layoutManager as LinearLayoutManager) {
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    isLoading = true
                    viewModel.fetchNextPage()
                }
            })
        }
    }

    private fun setUpObserves() {
        viewModel.apply {
            searchResult.observe(viewLifecycleOwner, { result ->
                lifecycleScope.launch {
                    binding!!.apply {
                        searchList.makeGone()
                        tvNoResult.makeGone()
                        delay(1000)
                        progressCircular.makeGone()
                    }

                    if (result != null && result.isNotEmpty()) {
                        binding.searchList.makeVisible()
                        searchResultAdapter.addAll(result)
                    } else {
                        binding.tvNoResult.makeVisible()
                    }
                }
            })

            nextResult.observe(viewLifecycleOwner, { result ->
                if (result.isNotEmpty()) {
                    searchResultAdapter.addItems(result)
                    isLoading = false
                } else {
                    isLastPage = true
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
                }

                viewModel.searchBeers(query)
            }
        } else {
            binding.searchLayout.makeSnackBar(getString(R.string.no_input_text_waring))
        }
    }

    private fun checkArgument() {
        val lastQuery = arguments?.getString(ARGS_KEY)
        if (!lastQuery.isNullOrEmpty()) {
            lifecycleScope.launch {
                binding!!.apply {
                    ivDefaultImg.makeGone()
                    searchList.makeGone()
                    progressCircular.makeVisible()

                    delay(1000)
                    progressCircular.makeGone()
                    searchList.makeVisible()
                }

                viewModel.searchBeers(lastQuery)
            }
        }
    }

    companion object {
        const val ARGS_KEY = "lastQuery"
    }
}