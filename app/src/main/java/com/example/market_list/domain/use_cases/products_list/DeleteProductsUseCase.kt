package com.example.market_list.domain.use_cases.products_list

import com.example.market_list.domain.model.ProductDomain
import com.example.market_list.domain.repository.MarketListRepository

class DeleteProductsUseCase(
    private val repository: MarketListRepository
) {
    suspend operator fun invoke(product: ProductDomain) = repository.deleteProduct(product)

}