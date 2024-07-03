package com.example.market_list.domain.useCases

import com.example.market_list.domain.model.MarketListDomain
import com.example.market_list.domain.repository.MarketListRepository

class UpdateMarketListUseCase(
    private val repository: MarketListRepository
) {
    suspend operator fun invoke(list: MarketListDomain) = repository.updateList(list)
}