package com.app.machinetest.remoteservice

import com.app.machinetest.models.Climate
import com.app.machinetest.models.TestApiResponseModel
import retrofit2.Response
import retrofit2.http.Url

interface ApiHelper {
    suspend fun getPosts(): Response<List<TestApiResponseModel>>
    suspend fun getWeatherData( urlData:String):Response<Climate>


}