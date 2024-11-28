package com.emirhan.yemekbook.service

import com.emirhan.yemekbook.model.foodData
import retrofit2.http.GET

interface foodAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json

    //GET, POST

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    // BASE_URL -> https://raw.githubusercontent.com/
    // atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json")
    suspend fun getFood():List<foodData>
}