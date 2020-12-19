package com.bentley.beerholic.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bentley.beerholic.R
import com.bentley.beerholic.data.remote.response.BeerModel
import com.bentley.beerholic.databinding.ItemBeerBinding

class SearchResultAdapter(private val list: MutableList<BeerModel>) :
    RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    private lateinit var bindingItem: ItemBeerBinding

    class SearchResultViewHolder(private val binding: ItemBeerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: BeerModel) {
            binding.apply {
                tvTitle.text = "Name: ${item.name}"
                tvAbv.text = "ABV: ${item.abv} %"
                tvDescription.text = "Description: ${item.description}"

                if (item.image_url != null) {
                    ivImage.load(item.image_url) {
                        crossfade(true)
                        placeholder(R.drawable.ic_placeholder)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        bindingItem = ItemBeerBinding.inflate(layoutInflater)
        return SearchResultViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size

    fun addAll(newList: List<BeerModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearAll() {
        list.clear()
        notifyDataSetChanged()
    }

    fun addItems(nextList: List<BeerModel>){
        list.addAll(nextList)
        notifyDataSetChanged()
    }
}