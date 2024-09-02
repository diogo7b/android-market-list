package com.example.market_list.domain.use_cases.main_list

import com.example.market_list.domain.model.MarketListDomain
import com.example.market_list.domain.repository.MarketListRepository

class UpdateListUseCase(
    private val repository: MarketListRepository
) {
    suspend operator fun invoke(list: MarketListDomain) = repository.updateList(list)
}