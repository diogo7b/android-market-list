package com.example.market_list.data

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val Context.db: AppDatabase
    get() = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "market_lists"
    ).fallbackToDestructiveMigration()
        .build()

private fun migrate(version: IntRange, sql: () -> String) =
    object : Migration(version.first, version.last) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(sql())
        }
    }