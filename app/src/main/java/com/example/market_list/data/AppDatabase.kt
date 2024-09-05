package com.example.market_list.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.market_list.data.dao.MarketListDao
import com.example.market_list.data.entity.ProductEntity
import com.example.market_list.data.entity.MarketListEntity

@Database(
    entities = [
        MarketListEntity::class,
        ProductEntity::class
    ],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun marketListDao(): MarketListDao
}