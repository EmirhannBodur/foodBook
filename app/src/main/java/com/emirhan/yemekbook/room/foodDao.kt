package com.emirhan.yemekbook.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirhan.yemekbook.model.foodData

@Dao
interface foodDao {

    @Insert
    suspend fun insertAll(vararg food:foodData):List<Long>

    @Query("SELECT * FROM foodData")
    suspend fun getAll():List<foodData>

    @Query("SELECT * FROM foodData WHERE id= :foodId")
    suspend fun getFood(foodId:Int):foodData

    @Query("DELETE FROM foodData")
    suspend fun deleteAll()
}