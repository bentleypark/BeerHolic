package com.bentley.beerholic.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.bentley.beerholic.R
import com.bentley.beerholic.data.remote.response.BeerModel
import com.bentley.beerholic.databinding.FragmentDetailBinding
import com.bentley.beerholic.ui.base.ViewBindingHolder
import com.bentley.beerholic.ui.base.ViewBindingHolderImpl
import com.bentley.beerholic.utils.makeGone
import com.bentley.beerholic.utils.makeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(),
    ViewBindingHolder<FragmentDetailBinding> by ViewBindingHolderImpl() {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = initBinding(FragmentDetailBinding.inflate(layoutInflater), this) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemId = arguments?.getString(ARGS_KEY)
        if (itemId != null) {
            viewModel.fetchDetails(itemId)
            binding!!.progressCircular.makeVisible()
        }

        setObserve()
    }

    private fun setObserve() {
        viewModel.result.observe(viewLifecycleOwner, { detail ->
            if (detail != null) {
                binding!!.progressCircular.makeGone()
                setUpView(detail)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setUpView(detail: BeerModel) {
        binding!!.apply {
            if (detail.image_url != null) {
                ivImage.load(detail.image_url) {
                    crossfade(true)
                    placeholder(R.drawable.ic_placeholder)
                }
            }

            tvTitle.text = "Name: ${detail.name}"
            tvAbv.text = "ABV: ${detail.abv} %"
            tvContents.text = "Description: ${detail.description}"
        }
    }

    companion object {
        private const val ARGS_KEY = "itemId"
    }
}