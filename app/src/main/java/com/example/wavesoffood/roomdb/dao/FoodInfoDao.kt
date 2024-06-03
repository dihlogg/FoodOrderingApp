package com.example.wavesoffood.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.wavesoffood.roomdb.entity.FoodInfo

@Dao
interface FoodInfoDao {
    @Query("select * from FoodInfos")
    fun getFoodInfos():List<FoodInfo>

    @Query("select * from FoodInfos where id = :itemId")
    fun getFoodInfo(itemId: String):FoodInfo

    @Insert
    fun insertFood(foodInfo: FoodInfo)

    @Update
    fun updateFood(foodInfo: FoodInfo)

    @Delete
    fun deleteFood(foodInfo: FoodInfo)

    @Query("DELETE FROM FoodInfos")
    fun clearAllFoodInfos()
}