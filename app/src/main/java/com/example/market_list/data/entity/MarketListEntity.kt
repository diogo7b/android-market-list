package com.example.market_list.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marketList")
data class MarketListEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "listName") val listName: String
)
