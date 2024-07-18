package com.example.market_list.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.market_list.data.entity.MarketListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarketListDao {
    @Query("SELECT * FROM marketList")
    fun getAllLists(): Flow<List<MarketListEntity>>

    @Insert
    fun insertList(list: MarketListEntity)

    @Update
    fun updateList(list: MarketListEntity)

    /*@Delete
    fun deleteList(id: Int)*/
}