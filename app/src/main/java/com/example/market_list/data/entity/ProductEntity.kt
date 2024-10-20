package com.example.market_list.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itens_list")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "unitPrice") val unitPrice: Double,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "totalPrice") val totalPrice: Double,
    @ColumnInfo(name = "marketListId") val marketListId: Int
)

