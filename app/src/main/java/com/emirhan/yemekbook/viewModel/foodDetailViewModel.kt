package com.emirhan.yemekbook.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.emirhan.yemekbook.model.foodData
import com.emirhan.yemekbook.room.foodDatabase
import kotlinx.coroutines.launch

class foodDetailViewModel(application: Application):AndroidViewModel(application) {

    val foodLiveDAta=MutableLiveData<foodData>()

    fun roomVerisiniAl(uuid:Int){
        viewModelScope.launch {
            val daoo=foodDatabase(getApplication()).dao()
            val besin=daoo.getFood(uuid)
            foodLiveDAta.value=besin
        }
    }
}