package com.example.market_list.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemList")
data class ItemListEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "unitPrice") val unitPrice: Double,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "totalPrice") val totalPrice: Double,
    @ColumnInfo(name = "marketListId") val marketListId: Int
)

