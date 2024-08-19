package com.example.market_list.ui.detailList

import com.example.market_list.domain.model.FullListDomain


sealed interface DetailListStates {
    object Loading : DetailListStates
    object Empty : DetailListStates
    data class Success(val marketList: List<FullListDomain>) : DetailListStates
    data class Error(val message: String) : DetailListStates
}