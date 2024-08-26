package com.example.market_list.domain.repository

import com.example.market_list.domain.model.FullListDomain
import com.example.market_list.domain.model.ItemListDomain
import com.example.market_list.domain.model.MarketListDomain
import kotlinx.coroutines.flow.Flow

interface MarketListRepository {
    // Crud main list
    suspend fun getAllLists(): Flow<List<MarketListDomain>>
    suspend fun insertList(list: MarketListDomain)
    suspend fun updateList(list: MarketListDomain)
    suspend fun deleteList(fullList: FullListDomain)

    // Crud products
    suspend fun getDetails(id: Int): Flow<FullListDomain>
    suspend fun insertProduct(product: ItemListDomain)
    suspend fun deleteProduct(product: ItemListDomain)
    suspend fun updateProduct(product: ItemListDomain)
}