package com.example.market_list.data.repository

import com.example.market_list.data.dao.MarketListDao
import com.example.market_list.data.mapper.FullListMapper.fullEntityToDomain
import com.example.market_list.data.mapper.MarketListMapper.toDomain
import com.example.market_list.data.mapper.MarketListMapper.toEntity
import com.example.market_list.data.mapper.ProductMapper.productToEntity
import com.example.market_list.domain.model.FullListDomain
import com.example.market_list.domain.model.MarketListDomain
import com.example.market_list.domain.model.ProductDomain
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

    override suspend fun updateList(list: MarketListDomain) = withContext(Dispatchers.IO) {
        dao.updateList(list.toEntity())
    }

    override suspend fun deleteList(marketList: MarketListDomain) = withContext(Dispatchers.IO) {
        dao.deleteList(marketList.toEntity())
    }

    override suspend fun getProducts(id: Int): Flow<FullListDomain> =
        withContext(Dispatchers.IO) {
            dao.getFullList(id).map {
                it.fullEntityToDomain()
            }
        }

    override suspend fun insertProduct(product: ProductDomain) = withContext(Dispatchers.IO) {
        dao.insertProduct(product.productToEntity())
    }

    override suspend fun deleteProduct(product: ProductDomain) = withContext(Dispatchers.IO) {
        dao.deleteProduct(product.productToEntity())
    }

    override suspend fun updateProduct(product: ProductDomain) = withContext(Dispatchers.IO) {
        dao.updateProduct(product.productToEntity())
    }
}
