package com.example.market_list.ui.detailList

import com.example.market_list.domain.model.FullListDomain


sealed interface DetailsStates {
    object Loading : DetailsStates
    object Empty : DetailsStates
    data class Success(val detailsList: FullListDomain) : DetailsStates
    data class Error(val message: String) : DetailsStates
}