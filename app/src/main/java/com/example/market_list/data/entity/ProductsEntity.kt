package com.example.market_list.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "unitPrice") val unitPrice: Double = 0.0,
    @ColumnInfo(name = "amount") val amount: Double = 0.0,
    @ColumnInfo(name = "marketListId") val marketListId: Int
)

