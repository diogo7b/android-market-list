package com.example.market_list.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.market_list.data.dao.MarketListDao
import com.example.market_list.data.entity.MarketListEntity

@Database(
    entities = [
        MarketListEntity::class,
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun marketListDao(): MarketListDao
}