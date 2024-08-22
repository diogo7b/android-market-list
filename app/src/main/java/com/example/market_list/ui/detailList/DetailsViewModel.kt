package com.example.market_list.ui.detailList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.market_list.domain.useCases.GetDetailsUseCase
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
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _state = MutableSharedFlow<DetailsStates>()
    val state: SharedFlow<DetailsStates> = _state

    init {
        getDetails()
    }

    private fun getDetails() = viewModelScope.launch {
        getDetailsUseCase(2)
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

}