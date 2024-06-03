package com.example.wavesoffood.roomdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wavesoffood.roomdb.dao.FoodInfoDao
import com.example.wavesoffood.roomdb.entity.FoodInfoRoom

@Database(entities = [FoodInfoRoom::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun FoodInfoDao():FoodInfoDao
}