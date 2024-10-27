package com.example.market_list.domain.use_cases.products_list

import com.example.market_list.domain.model.ProductDomain
import com.example.market_list.domain.repository.MarketListRepository

class InsertProductUseCase(private val repository: MarketListRepository) {

    suspend operator fun invoke(name: String, price: String, amount: String, marketListId: Int) =
        repository.insertProduct(
            ProductDomain(
                name = name,
                price = price.toDouble(),
                amount = amount.toDouble(),
                marketListId = marketListId
            )
        )
}