package com.example.market_list.domain.repository

import com.example.market_list.domain.model.ItemListDomain
import com.example.market_list.domain.model.MarketListDomain

interface MarketListRepository {
    // Crud main list
    suspend fun getAllLists(): List<MarketListDomain>
    suspend fun insertList(list: MarketListDomain)
    suspend fun deleteList(id:Int)
    suspend fun updateList(list: MarketListDomain)
    // Crud products
    suspend fun getAllProducts(): List<ItemListDomain>
    suspend fun insertProduct(product: ItemListDomain)
    suspend fun deleteProduct(product: ItemListDomain)
    suspend fun updateProduct(product: ItemListDomain)
}