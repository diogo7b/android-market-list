package com.example.market_list.domain.use_cases.details_list

import com.example.market_list.domain.model.ItemListDomain
import com.example.market_list.domain.repository.MarketListRepository

class InsertProductUseCase(private val repository: MarketListRepository) {
    suspend operator fun invoke(item: ItemListDomain) = repository.insertProduct(item)
}