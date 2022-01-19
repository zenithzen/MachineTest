package com.app.machinetest.remoteservice

import com.app.machinetest.models.Climate
import retrofit2.Response

interface ApiHelper {

    suspend fun getWeatherData( urlData:String):Response<Climate>


}