package com.example.market_list.domain.repository

import com.example.market_list.domain.model.ItemListDomain
import com.example.market_list.domain.model.MarketListDomain
import kotlinx.coroutines.flow.Flow

interface MarketListRepository {
    // Crud main list
    suspend fun getAllLists(): Flow<List<MarketListDomain>>
    suspend fun insertList(list: MarketListDomain)
    suspend fun updateList(list: MarketListDomain)
    suspend fun deleteList(id:Int)
    // Crud products
    suspend fun getAllProducts(): Flow<List<ItemListDomain>>
    suspend fun insertProduct(product: ItemListDomain)
    suspend fun deleteProduct(product: ItemListDomain)
    suspend fun updateProduct(product: ItemListDomain)
}