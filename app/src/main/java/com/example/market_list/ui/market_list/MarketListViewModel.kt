package com.example.market_list.ui.market_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.market_list.data.db
import com.example.market_list.data.repository.MarketListRepositoryImpl
import com.example.market_list.domain.model.MarketListDomain
import com.example.market_list.domain.use_cases.market_list.DeleteListUseCase
import com.example.market_list.domain.use_cases.market_list.GetAllListsUseCase
import com.example.market_list.domain.use_cases.market_list.InsertListUseCase
import com.example.market_list.domain.use_cases.market_list.UpdateListUseCase
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
    private val deleteListUseCase: DeleteListUseCase,
    private val updateListUseCase: UpdateListUseCase,
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

    fun deleteList(listName: MarketListDomain) = viewModelScope.launch {
        deleteListUseCase(listName)
    }

    fun updateList(id: Int, name: String) = viewModelScope.launch {
        updateListUseCase(MarketListDomain(id = id, listName = name))
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
            val deleteListUseCase = DeleteListUseCase(repository)
            val updateListUseCase = UpdateListUseCase(repository)

            return MarketListViewModel(
                getAllListsUseCase,
                insertListUseCase,
                deleteListUseCase,
                updateListUseCase
            ) as T
        }
    }
}

