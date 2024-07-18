package com.example.market_list.ui.mainlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.market_list.databinding.ItemMarketListBinding
import com.example.market_list.domain.model.MarketListDomain

class MarketListAdapter :
    ListAdapter<MarketListDomain, MarketListAdapter.ViewHolder>(DiffCallback()) {

    var click: (MarketListDomain) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMarketListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemMarketListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MarketListDomain) {
            binding.tvTitleList.text = item.listName
            binding.root.setOnClickListener {
                click(item)
            }
        }

    }
}

class DiffCallback : DiffUtil.ItemCallback<MarketListDomain>() {
    override fun areItemsTheSame(oldItem: MarketListDomain, newItem: MarketListDomain) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MarketListDomain, newItem: MarketListDomain) =
        oldItem.id == newItem.id
}