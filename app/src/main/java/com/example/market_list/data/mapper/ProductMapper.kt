package com.example.market_list.data.mapper

import com.example.market_list.data.entity.ProductEntity
import com.example.market_list.domain.model.ProductDomain

object ProductMapper {
    fun ProductEntity.productToDomain() = ProductDomain(
        id = id,
        name = name,
        unitPrice = unitPrice,
        amount = amount,
        totalPrice = totalPrice,
        marketListId = marketListId
    )

    fun ProductDomain.productToEntity() = ProductEntity(
        id = id,
        name = name,
        unitPrice = unitPrice,
        amount = amount,
        totalPrice = totalPrice,
        marketListId = marketListId
    )
}