package com.example.market_list.data.entity

import androidx.room.Embedded
import androidx.room.Relation


data class FullListEntity(
    @Embedded val marketList: MarketListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "marketListId"
    ) val products: List<ItemListEntity>,

)
