package com.emirhan.yemekbook.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class ozelSharedPreference {
    companion object{
        private val ZAMAN="zaman"
        private var sharedPreference:SharedPreferences?=null

        @Volatile private var instance:ozelSharedPreference?=null
        private val lock=Any()
        operator fun invoke(context: Context):ozelSharedPreference= instance?: synchronized(lock){
            instance?: ozelSharedPreferenceYap(context).also {
                instance=it

            }
        }
        private fun ozelSharedPreferenceYap(context: Context):ozelSharedPreference{
            sharedPreference=PreferenceManager.getDefaultSharedPreferences(context)
            return ozelSharedPreference()
        }
    }
    fun zamaniKaydet(zaman:Long){
        sharedPreference?.edit()?.putLong(ZAMAN,zaman)?.apply()
    }
    fun zamaniAl()= sharedPreference?.getLong(ZAMAN,0)
}