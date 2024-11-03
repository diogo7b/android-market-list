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

    var update: (ProductDomain) -> Unit = {}
    var delete: (ProductDomain) -> Unit = {}

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

        fun bind(product: ProductDomain) = with(binding) {
            product.apply {
                tvNameItem.text = name
                tvTotalPrice.text = "R$ $price"
                tvAmount.text = amount.toString()
            }
            ivDelete.setOnClickListener { delete(product) }
            binding.root.setOnClickListener { update(product) }

        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ProductDomain>() {

    override fun areItemsTheSame(oldItem: ProductDomain, newItem: ProductDomain) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ProductDomain, newItem: ProductDomain) =
        oldItem.id == newItem.id
}


