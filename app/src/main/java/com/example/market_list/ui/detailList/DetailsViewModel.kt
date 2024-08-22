package com.example.market_list.ui.detailList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.market_list.data.db
import com.example.market_list.data.repository.MarketListRepositoryImpl
import com.example.market_list.domain.model.ItemListDomain
import com.example.market_list.domain.useCases.GetDetailsUseCase
import com.example.market_list.domain.useCases.InsertProductUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val insertProductUseCase: InsertProductUseCase,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _state = MutableSharedFlow<DetailsStates>()
    val state: SharedFlow<DetailsStates> = _state

    fun getDetails(id: Int) = viewModelScope.launch {
        getDetailsUseCase(id)
            .flowOn(dispatcherIO)
            .onStart {
                _state.emit(DetailsStates.Loading)
            }.catch {
                _state.emit(DetailsStates.Error(it.toString()))
            }.collect { details ->
                if (details.products.isEmpty()) {
                    _state.emit(DetailsStates.Empty)
                } else {
                    _state.emit(DetailsStates.Success(details))
                }
            }
    }

    fun insertProduct(product: ItemListDomain) = viewModelScope.launch {
        insertProductUseCase(product)
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application =
                checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repository = MarketListRepositoryImpl(application.db.marketListDao())
            val getDetailsUseCase = GetDetailsUseCase(repository)
            val insertProductUseCase = InsertProductUseCase(repository)

            return DetailsViewModel(getDetailsUseCase, insertProductUseCase) as T
        }
    }

}