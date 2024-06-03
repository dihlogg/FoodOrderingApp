package com.example.wavesoffood.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FoodInfoRooms")
class FoodInfoRoom(@ColumnInfo(name = "id") var id:String,
               @ColumnInfo(name = "name") var name:String,
               @ColumnInfo(name = "price") var price:Double,
               @ColumnInfo(name = "imageMenu") var imageMenu:String,
               @ColumnInfo(name = "imageDetails") var imageDetails:String,
               @ColumnInfo(name = "description") var description:String,
               @ColumnInfo(name = "ingredient") var ingredient:String,
               @ColumnInfo(name = "quantity") var quantity:Int,
                   @ColumnInfo(name = "foodQuantity") var foodQuantity:String,
               @ColumnInfo(name = "userId") var userId: String?
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idAuto")
    var idAuto:Int = 0
}