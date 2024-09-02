package com.example.market_list.domain.repository

import com.example.market_list.domain.model.FullListDomain
import com.example.market_list.domain.model.ProductDomain
import com.example.market_list.domain.model.MarketListDomain
import kotlinx.coroutines.flow.Flow

interface MarketListRepository {
    // Crud main list
    suspend fun getAllLists(): Flow<List<MarketListDomain>>
    suspend fun insertList(list: MarketListDomain)
    suspend fun updateList(list: MarketListDomain)
    suspend fun deleteList(marketList: MarketListDomain)

    // Crud products
    suspend fun getProducts(id: Int): Flow<FullListDomain>
    suspend fun insertProduct(product: ProductDomain)
    suspend fun deleteProduct(product: ProductDomain)
    suspend fun updateProduct(product: ProductDomain)
}