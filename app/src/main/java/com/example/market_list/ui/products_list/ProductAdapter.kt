package com.example.market_list.ui.products_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.market_list.databinding.LayoutProductListBinding
import com.example.market_list.domain.model.ProductDomain

class DetailsAdapter() :
    ListAdapter<ProductDomain, DetailsAdapter.ViewHolder>(DiffCallback()) {

    var click: (ProductDomain) -> Unit = {}
    var longClick: (ProductDomain) -> Unit = {}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutProductListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: LayoutProductListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductDomain) {
            item.apply {
                binding.tvNameItem.text = name
                binding.tvTotalPrice.text = "R$ ${price}"
                binding.tvAmount.text = amount.toString()
            }

            binding.root.setOnLongClickListener {
                longClick(item)
                true
            }

        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ProductDomain>() {

    override fun areItemsTheSame(oldItem: ProductDomain, newItem: ProductDomain) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ProductDomain, newItem: ProductDomain) =
        oldItem.id == newItem.id
}


