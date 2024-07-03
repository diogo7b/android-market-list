package com.example.market_list.domain.useCases

import com.example.market_list.domain.repository.MarketListRepository

class DeleteListUseCase(
    private val repository: MarketListRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteList(id)

}