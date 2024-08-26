package com.example.market_list.domain.use_cases.main_list

import com.example.market_list.domain.model.FullListDomain
import com.example.market_list.domain.repository.MarketListRepository

class DeleteListUseCase(
    private val repository: MarketListRepository
) {
    suspend operator fun invoke(fullList:FullListDomain) = repository.deleteList(fullList)

}