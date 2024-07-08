package com.example.market_list.ui.mainlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market_list.domain.useCases.GetAllListsUseCase
import com.example.market_list.domain.useCases.InsertListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MarketListViewModel(
    private val getAllListsUseCase: GetAllListsUseCase,
    private val insertListUseCase: InsertListUseCase,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow<MarketListState>(MarketListState.Empty)
    val state: StateFlow<MarketListState> = _state.asStateFlow()



    init {
        TODO("call function to be initialized")
    }

    private fun getAllLists() = viewModelScope.launch(){
        getAllListsUseCase()



    }
}
