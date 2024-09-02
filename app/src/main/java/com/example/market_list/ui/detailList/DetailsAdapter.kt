package com.example.market_list.ui.detailList


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.market_list.databinding.ItemDetailListBinding
import com.example.market_list.domain.model.FullListDomain
import com.example.market_list.domain.model.ItemListDomain

class DetailsAdapter() :
    ListAdapter<ItemListDomain, DetailsAdapter.ViewHolder>(DiffCallback()) {

    var total: Double = 0.0
    var click: (ItemListDomain) -> Unit = {}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDetailListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemDetailListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemListDomain) {
            item.apply {
                binding.tvNameItem.text = name
                binding.tvUnitPrice.text = unitPrice.toString()
                binding.tvAmount.text = amount.toString()
                binding.tvTotalPrice.text = totalPrice.toString()
            }
        }
    }
}


class DiffCallback : DiffUtil.ItemCallback<ItemListDomain>() {
    override fun areItemsTheSame(oldItem: ItemListDomain, newItem: ItemListDomain) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ItemListDomain, newItem: ItemListDomain) =
        oldItem.id == newItem.id
}


