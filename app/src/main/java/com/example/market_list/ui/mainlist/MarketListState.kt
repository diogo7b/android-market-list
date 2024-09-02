package com.example.market_list.ui.mainlist

import com.example.market_list.domain.model.MarketListDomain

sealed interface MarketListState {
    object Loading : MarketListState
    object Empty : MarketListState
    data class Success(val marketList: List<MarketListDomain>) : MarketListState
    data class Error(val message: String) : MarketListState
}