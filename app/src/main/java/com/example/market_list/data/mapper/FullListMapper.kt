package com.example.market_list.data.mapper

import com.example.market_list.data.entity.FullListEntity
import com.example.market_list.data.entity.ItemListEntity
import com.example.market_list.data.entity.MarketListEntity
import com.example.market_list.domain.model.FullListDomain
import com.example.market_list.domain.model.ItemListDomain
import com.example.market_list.domain.model.MarketListDomain

object FullListMapper {
    fun FullListEntity.fullEntityToDomain() = FullListDomain(
        marketList = MarketListDomain(id = marketList.id, listName = marketList.listName),
        products = products.map {
            ItemListDomain(
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
            ItemListEntity(
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