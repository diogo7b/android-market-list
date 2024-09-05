package com.example.market_list.domain.use_cases.market_list

import com.example.market_list.domain.repository.MarketListRepository

class GetAllListsUseCase(
    private val repository: MarketListRepository
) {
    suspend operator fun invoke() = repository.getAllLists()
}

