package com.example.wavesoffood.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FoodInfos")
class FoodInfo(@ColumnInfo(name = "id") var id:String,
               @ColumnInfo(name = "name") var name:String,
               @ColumnInfo(name = "price") var price:Double,
               @ColumnInfo(name = "imageMenu") var imageMenu:String,
               @ColumnInfo(name = "imageDetails") var imageDetails:String,
               @ColumnInfo(name = "description") var description:String,
               @ColumnInfo(name = "ingredient") var ingredient:String)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idAuto")
    var idAuto:Int = 0
}