package com.example.market_list.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.market_list.data.entity.FullListEntity
import com.example.market_list.data.entity.ItemListEntity
import com.example.market_list.data.entity.MarketListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarketListDao {
    @Query("SELECT * FROM markets_list")
    fun getAllLists(): Flow<List<MarketListEntity>>

    @Insert
    fun insertList(list: MarketListEntity)

    @Update
    fun updateList(list: MarketListEntity)

    @Transaction
    @Query("SELECT * FROM  markets_list where id = :id")
    fun getFullList(id: Int): Flow<FullListEntity>

    @Insert
    fun insertItem(item: ItemListEntity)

    @Update
    fun updateItem(item: ItemListEntity)

    @Delete
    fun deleteItem(item: ItemListEntity)
}