package com.example.market_list.domain.use_cases.products_list

import com.example.market_list.domain.model.ProductDomain
import com.example.market_list.domain.repository.MarketListRepository

class UpdateProductUseCase(
    private val repository: MarketListRepository
) {
    suspend operator fun invoke(product: ProductDomain) = repository.updateProduct(product)
}