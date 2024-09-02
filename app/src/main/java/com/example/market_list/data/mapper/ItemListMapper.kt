package com.example.market_list.data.mapper

import com.example.market_list.data.entity.ItemListEntity
import com.example.market_list.domain.model.ItemListDomain

object ItemListMapper {
    fun ItemListEntity.itemLisToDomain() = ItemListDomain(
        id = id,
        name = name,
        unitPrice = unitPrice,
        amount = amount,
        totalPrice = totalPrice,
        marketListId = marketListId
    )

    fun ItemListDomain.itemLisTtoEntity() = ItemListEntity(
        id = id,
        name = name,
        unitPrice = unitPrice,
        amount = amount,
        totalPrice = totalPrice,
        marketListId = marketListId
    )
}