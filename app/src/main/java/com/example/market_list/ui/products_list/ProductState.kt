package com.example.market_list.ui.products_list

import com.example.market_list.domain.model.FullListDomain


sealed interface ProductState {
    object Loading : ProductState
    object Empty : ProductState
    data class Success(val detailsList: FullListDomain) : ProductState
    data class Error(val message: String) : ProductState
}