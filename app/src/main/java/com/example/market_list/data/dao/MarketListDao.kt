package com.example.market_list.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.market_list.data.entity.MarketListEntity

@Dao
interface MarketListDao {
    @Query("SELECT * FROM marketList")
    suspend fun getAllLists(): List<MarketListEntity>

    @Insert
    suspend fun insertList(list: MarketListEntity)

    @Update
    suspend fun updateList(list: MarketListEntity)

    /*@Delete
    suspend fun deleteList(id: Int)*/
}