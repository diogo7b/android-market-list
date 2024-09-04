package com.example.market_list.ui.products_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.market_list.data.db
import com.example.market_list.data.repository.MarketListRepositoryImpl
import com.example.market_list.domain.use_cases.products_list.GetProductsUseCase
import com.example.market_list.domain.use_cases.products_list.InsertProductUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getDetailsUseCase: GetProductsUseCase,
    private val insertProductUseCase: InsertProductUseCase,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableSharedFlow<ProductState>()
    val state: SharedFlow<ProductState> = _state

    fun getDetails(id: Int) = viewModelScope.launch {
        getDetailsUseCase(id)
            .flowOn(dispatcherIO)
            .onStart {
                _state.emit(ProductState.Loading)
            }.catch {
                _state.emit(ProductState.Error(it.toString()))
            }.collect { details ->
                if (details.products.isEmpty()) {
                    _state.emit(ProductState.Empty)
                } else {
                    _state.emit(ProductState.Success(details))
                }
            }
    }

    fun insertProduct(name: String, price: String, amount: String, listId: Int) = viewModelScope.launch {
        insertProductUseCase(name, price, amount, listId)
    }

    class Factory : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application =
                checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repository = MarketListRepositoryImpl(application.db.marketListDao())
            val getDetailsUseCase = GetProductsUseCase(repository)
            val insertProductUseCase = InsertProductUseCase(repository)

            return ProductViewModel(getDetailsUseCase, insertProductUseCase) as T
        }
    }

}