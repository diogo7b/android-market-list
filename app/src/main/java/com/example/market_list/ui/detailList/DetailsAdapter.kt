package com.example.market_list.ui.detailList


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.market_list.databinding.ItemDetailListBinding
import com.example.market_list.domain.model.FullListDomain

class DetailsAdapter() :
    ListAdapter<FullListDomain, DetailsAdapter.ViewHolder>(DiffCallback()) {

    var titleList: String = ""
    var total: Double = 0.0

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
        fun bind(item: FullListDomain) {
            titleList = item.marketList.listName
            item.products.map {
                binding.tvNameItem.text = it.name
                binding.tvUnitPrice.text = it.unitPrice.toString()
                binding.tvAmount.text = it.amount.toString()
                binding.tvTotalPrice.text = it.totalPrice.toString()
            }

            total = item.products.map { it.totalPrice }
                .sum()

        }
    }
}


class DiffCallback : DiffUtil.ItemCallback<FullListDomain>() {
    override fun areItemsTheSame(oldItem: FullListDomain, newItem: FullListDomain) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: FullListDomain, newItem: FullListDomain) =
        oldItem.marketList.id == newItem.marketList.id
}


