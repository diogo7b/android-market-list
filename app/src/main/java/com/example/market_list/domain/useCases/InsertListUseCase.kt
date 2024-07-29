package com.example.market_list.domain.useCases

import com.example.market_list.domain.model.MarketListDomain
import com.example.market_list.domain.repository.MarketListRepository

class InsertListUseCase(
    private val repository: MarketListRepository
) {
    suspend operator fun invoke(listName: String) =
        repository.insertList(MarketListDomain(listName = listName))
}