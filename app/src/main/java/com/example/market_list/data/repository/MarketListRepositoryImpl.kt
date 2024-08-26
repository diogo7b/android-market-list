package com.example.market_list.data.repository

import android.util.Log
import com.example.market_list.data.dao.MarketListDao
import com.example.market_list.data.mapper.FullListMapper.fullEntityToDomain
import com.example.market_list.data.mapper.ItemListMapper.itemLisTtoEntity
import com.example.market_list.data.mapper.MarketListMapper.toDomain
import com.example.market_list.data.mapper.MarketListMapper.toEntity
import com.example.market_list.domain.model.FullListDomain
import com.example.market_list.domain.model.ItemListDomain
import com.example.market_list.domain.model.MarketListDomain
import com.example.market_list.domain.repository.MarketListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MarketListRepositoryImpl(private val dao: MarketListDao) : MarketListRepository {

    override suspend fun getAllLists():
            Flow<List<MarketListDomain>> = withContext(Dispatchers.IO) {
        dao.getAllLists().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun insertList(list: MarketListDomain) = withContext(Dispatchers.IO) {
        dao.insertList(list.toEntity())
    }

    override suspend fun updateList(list: MarketListDomain) {
        dao.updateList(list.toEntity())
    }

    override suspend fun deleteList(fullList: FullListDomain) {
        dao.deleteList(fullList)
    }

    override suspend fun getDetails(id: Int): Flow<FullListDomain> =
        withContext(Dispatchers.IO) {
            dao.getFullList(id).map {
                Log.e("teste", it.toString())
                it.fullEntityToDomain()
            }
        }

    override suspend fun insertProduct(product: ItemListDomain) {
        dao.insertItem(product.itemLisTtoEntity())
    }

    override suspend fun deleteProduct(product: ItemListDomain) {
        dao.deleteItem(product.itemLisTtoEntity())
    }

    override suspend fun updateProduct(product: ItemListDomain) {
        dao.updateItem(product.itemLisTtoEntity())
    }
}
