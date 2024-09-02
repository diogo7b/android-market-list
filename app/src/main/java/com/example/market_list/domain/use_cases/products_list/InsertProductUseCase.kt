package com.example.market_list.domain.use_cases.products_list

import com.example.market_list.domain.model.ProductDomain
import com.example.market_list.domain.repository.MarketListRepository

class InsertProductUseCase(private val repository: MarketListRepository) {
    suspend operator fun invoke(item: ProductDomain) = repository.insertProduct(item)
}