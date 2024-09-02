package com.example.market_list.ui.products_list

import com.example.market_list.domain.model.FullListDomain


sealed interface ProductStates {
    object Loading : ProductStates
    object Empty : ProductStates
    data class Success(val detailsList: FullListDomain) : ProductStates
    data class Error(val message: String) : ProductStates
}