package com.example.market_list.domain.use_cases.details_list

import com.example.market_list.domain.repository.MarketListRepository

class GetDetailsUseCase(
    private val repository: MarketListRepository

) {
    suspend operator fun invoke(id: Int) = repository.getDetails(id)

}