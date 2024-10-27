package com.example.market_list.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.market_list.data.dao.MarketListDao
import com.example.market_list.data.entity.MarketListEntity
import com.example.market_list.data.entity.ProductsEntity

@Database(
    entities = [
        MarketListEntity::class,
        ProductsEntity::class
    ],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun marketListDao(): MarketListDao
}