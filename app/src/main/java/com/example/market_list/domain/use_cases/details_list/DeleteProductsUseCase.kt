package com.example.market_list.domain.use_cases.details_list

import com.example.market_list.domain.model.ItemListDomain
import com.example.market_list.domain.repository.MarketListRepository

class DeleteProductsUseCase(
    private val repository: MarketListRepository
) {
    suspend operator fun invoke(product: ItemListDomain) = repository.deleteProduct(product)

}