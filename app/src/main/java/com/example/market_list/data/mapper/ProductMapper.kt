package com.example.market_list.data.mapper

import com.example.market_list.data.entity.ProductsEntity
import com.example.market_list.domain.model.ProductDomain

object ProductMapper {
    fun ProductsEntity.productToDomain() = ProductDomain(
        id = id,
        name = name,
        price = unitPrice,
        amount = amount,
        marketListId = marketListId
    )

    fun ProductDomain.productToEntity() = ProductsEntity(
        id = id,
        name = name,
        unitPrice = price,
        amount = amount,
        marketListId = marketListId
    )
}