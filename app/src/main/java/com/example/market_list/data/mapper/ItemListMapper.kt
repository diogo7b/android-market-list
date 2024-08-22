package com.example.market_list.data.mapper

import com.example.market_list.data.entity.ItemListEntity
import com.example.market_list.domain.model.ItemListDomain

object ItemListMapper {
    fun ItemListEntity.toDomain() = ItemListDomain(
        id = id,
        name = name,
        unitPrice = unitPrice,
        amount = amount,
        totalPrice = totalPrice,
        marketListId = marketListId
    )

    fun ItemListDomain.toEntity() = ItemListEntity(
        id = id,
        name = name,
        unitPrice = unitPrice,
        amount = amount,
        totalPrice = totalPrice,
        marketListId = marketListId
    )
}