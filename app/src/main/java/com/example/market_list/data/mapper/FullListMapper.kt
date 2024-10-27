package com.example.market_list.data.mapper

import com.example.market_list.data.entity.FullListEntity
import com.example.market_list.data.entity.MarketListEntity
import com.example.market_list.data.entity.ProductsEntity
import com.example.market_list.domain.model.FullListDomain
import com.example.market_list.domain.model.ProductDomain
import com.example.market_list.domain.model.MarketListDomain

object FullListMapper {
    fun FullListEntity.fullEntityToDomain() = FullListDomain(
        marketList = MarketListDomain(id = marketList.id, listName = marketList.listName),
        products = products.map {
            ProductDomain(
                id = it.id,
                name = it.name,
                price = it.unitPrice,
                amount = it.amount,
                marketListId = it.marketListId
            )
        }
    )

    fun FullListDomain.dullDomainToEntity() = FullListEntity(
        marketList = MarketListEntity(id = marketList.id, listName = marketList.listName),
        products = products.map {
            ProductsEntity(
                id = it.id,
                name = it.name,
                unitPrice = it.price,
                amount = it.amount,
                marketListId = it.marketListId
            )
        }
    )
}