package com.example.wavesoffood.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.wavesoffood.roomdb.entity.FoodInfo

@Dao
interface FoodInfoDao {
    @Query("select * from FoodInfos")
    fun getFoodInfos():List<FoodInfo>

    @Insert
    fun insertFood(foodInfo: FoodInfo)
}