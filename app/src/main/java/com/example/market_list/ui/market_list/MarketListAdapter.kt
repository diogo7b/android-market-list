package com.example.market_list.ui.market_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.market_list.databinding.LayoutMarketListBinding
import com.example.market_list.domain.model.MarketListDomain

class MarketListAdapter() :
    ListAdapter<MarketListDomain, MarketListAdapter.ViewHolder>(DiffCallback()) {

    var click: (MarketListDomain) -> Unit = {}
    var delete: (MarketListDomain) -> Unit = {}
    var update: (MarketListDomain) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutMarketListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: LayoutMarketListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(list: MarketListDomain) = with(binding) {
            tvTitleList.text = list.listName
            root.setOnClickListener {
                click(list)
            }
            ivDelete.setOnClickListener { delete(list) }
            root.setOnLongClickListener {
                update(list)
                true
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