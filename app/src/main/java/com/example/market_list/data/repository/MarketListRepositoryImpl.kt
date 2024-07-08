package com.example.market_list.data.repository

import com.example.market_list.data.dao.MarketListDao
import com.example.market_list.data.entity.MarketListEntity
import com.example.market_list.domain.model.ItemListDomain
import com.example.market_list.domain.model.MarketListDomain
import com.example.market_list.domain.repository.MarketListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarketListRepositoryImpl(private val dao: MarketListDao) : MarketListRepository {
    override suspend fun getAllLists(): List<MarketListDomain> = withContext(Dispatchers.IO) {
        dao.getAllLists().map { it.toDomain() }
    }

    override suspend fun insertList(list: MarketListDomain) {
        dao.insertList(list.toEntity())
    }

    override suspend fun updateList(list: MarketListDomain) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteList(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllProducts(): List<ItemListDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun insertProduct(product: ItemListDomain) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(product: ItemListDomain) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProduct(product: ItemListDomain) {
        TODO("Not yet implemented")
    }
}

// Extension
private fun MarketListEntity.toDomain() = MarketListDomain(
    id = id,
    listName = listName
)

private fun MarketListDomain.toEntity() = MarketListEntity(
    id = id,
    listName = listName
)