package com.example.market_list.data.mapper

import com.example.market_list.data.entity.FullListEntity
import com.example.market_list.data.entity.ProductEntity
import com.example.market_list.data.entity.MarketListEntity
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
                unitPrice = it.unitPrice,
                amount = it.amount,
                totalPrice = it.totalPrice,
                marketListId = it.marketListId
            )
        }
    )

    fun FullListDomain.dullDomainToEntity() = FullListEntity(
        marketList = MarketListEntity(id = marketList.id, listName = marketList.listName),
        products = products.map {
            ProductEntity(
                id = it.id,
                name = it.name,
                unitPrice = it.unitPrice,
                amount = it.amount,
                totalPrice = it.totalPrice,
                marketListId = it.marketListId
            )
        }
    )
}