package com.example.market_list.data.mapper

import com.example.market_list.data.entity.MarketListEntity
import com.example.market_list.domain.model.MarketListDomain

fun MarketListEntity.toDomain() = MarketListDomain(
    id = id,
    listName = listName
)

fun MarketListDomain.toEntity() = MarketListEntity(
    id = id,
    listName = listName
)