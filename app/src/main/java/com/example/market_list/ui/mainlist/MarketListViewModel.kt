package com.example.market_list.ui.mainlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.market_list.data.repository.MarketListRepositoryImpl
import com.example.market_list.domain.use_cases.main_list.GetAllListsUseCase
import com.example.market_list.domain.use_cases.main_list.InsertListUseCase
import com.example.market_list.data.db
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class MarketListViewModel(
    private val getAllListsUseCase: GetAllListsUseCase,
    private val insertListUseCase: InsertListUseCase,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableSharedFlow<MarketListState>()
    val state: SharedFlow<MarketListState> = _state

    init {
        getAllLists()
    }

    private fun getAllLists() = viewModelScope.launch {
        getAllListsUseCase()
            .flowOn(dispatcherIO)
            .onStart {
                _state.emit(MarketListState.Loading)
            }.catch {
                _state.emit(MarketListState.Error(it.toString()))
            }.collect { lists ->
                if (lists.isEmpty()) {
                    _state.emit(MarketListState.Empty)
                } else {
                    _state.emit(MarketListState.Success(lists))
                }
            }
    }

    fun insertList(listName: String) = viewModelScope.launch {
        insertListUseCase(listName)
    }


    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application =
                checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repository = MarketListRepositoryImpl(application.db.marketListDao())
            val getAllListsUseCase = GetAllListsUseCase(repository)
            val insertListUseCase = InsertListUseCase(repository)

            return MarketListViewModel(getAllListsUseCase, insertListUseCase) as T
        }
    }
}

