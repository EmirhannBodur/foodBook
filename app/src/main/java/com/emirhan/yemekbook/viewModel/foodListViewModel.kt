package com.emirhan.yemekbook.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirhan.yemekbook.model.foodData
import com.emirhan.yemekbook.room.foodDatabase
import com.emirhan.yemekbook.service.foodApiService
import com.emirhan.yemekbook.util.ozelSharedPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class foodListViewModel(application: Application) :AndroidViewModel(application){

    val foods=MutableLiveData<List<foodData>>()
    val foodErrorMessage=MutableLiveData<Boolean>( )
    val foodLoading=MutableLiveData<Boolean>()
    private  var uptadeTime=10*60*1000*1000*1000L

    fun refreshData()
    {
        val savedTime=OzelSharedPreferences.zamaniAl()
        if (savedTime!=null&&savedTime!=0L&&System.nanoTime()-savedTime<uptadeTime){
            verileriRoomdanAl()
        }else
        {
            verileriNettenAl()
        }
    }
    private fun verileriRoomdanAl(){
        foodLoading.value=true
        viewModelScope.launch(Dispatchers.IO) {
            val besinler=foodDatabase(getApplication()).dao().getAll()
            withContext(Dispatchers.Main){
                foodsShow(besinler)
            }
        }
    }
    fun refreshDataInternet(){
        verileriNettenAl()
    }
    private val OzelSharedPreferences=ozelSharedPreference(getApplication())
    private val FoodApiService=foodApiService()

    private fun verileriNettenAl(){
        foodLoading.value=true
        viewModelScope.launch(Dispatchers.IO) {
           val besinler= FoodApiService.getData()
            withContext(Dispatchers.Main){
                foodLoading.value=false
                roomSaving(besinler)
            }
        }

    }
    private fun foodsShow(foodListesi: List<foodData>){
        foods.value=foodListesi
        foodLoading.value=false
        foodErrorMessage.value=false

    }

    private fun roomSaving( foodListesi:List<foodData>){
        viewModelScope.launch {
            val daoo=foodDatabase(getApplication()).dao()
            daoo.deleteAll()
            val idList= daoo.insertAll(*foodListesi.toTypedArray())
            var i=0
            while (i<foodListesi.size){
                foodListesi[i].id=idList[i].toInt()
                i=i+1
            }
            foodsShow(foodListesi)
        }
        OzelSharedPreferences.zamaniKaydet(System.nanoTime())
    }



}

