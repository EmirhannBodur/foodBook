package com.emirhan.yemekbook.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emirhan.yemekbook.model.foodData

@Database(entities =[foodData::class], version = 1)
abstract class foodDatabase:RoomDatabase(){
    abstract fun dao():foodDao

    companion object{
        @Volatile
        private var instance :foodDatabase?= null

        private val lock=Any()

        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?: databaseOlustur(context).also {
                instance=it
            }
        }
        private fun databaseOlustur(context: Context)=Room.databaseBuilder(
            context.applicationContext,
            foodDatabase::class.java,"foodDatabase"
        ).build()

    }
}