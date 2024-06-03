package com.example.wavesoffood.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.wavesoffood.roomdb.entity.FoodInfoRoom

@Dao
interface FoodInfoDao {
    @Query("select * from FoodInfoRooms where userId = :userId")
    fun getFoodInfos(userId: String):List<FoodInfoRoom>

    @Query("select * from FoodInfoRooms where id = :itemId and userId = :userId LIMIT 1")
    fun getFoodInfo(itemId: String, userId: String):FoodInfoRoom

    @Insert
    fun insertFood(foodInfo: FoodInfoRoom)

    @Update
    fun updateFood(foodInfo: FoodInfoRoom)

    @Delete
    fun deleteFood(foodInfo: FoodInfoRoom)

    @Query("DELETE FROM FoodInfoRooms where userId = :userId")
    fun clearAllFoodInfos(userId: String)
}