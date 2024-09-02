package com.example.market_list.domain.use_cases.products_list

import com.example.market_list.domain.repository.MarketListRepository

class GetProductsUseCase(
    private val repository: MarketListRepository

) {
    suspend operator fun invoke(id: Int) = repository.getProducts(id)

}