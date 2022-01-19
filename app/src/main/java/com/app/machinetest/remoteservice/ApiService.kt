package com.app.machinetest.remoteservice

import com.app.machinetest.models.Climate
import com.app.machinetest.models.TestApiResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<TestApiResponseModel>>

    @GET
    suspend fun getWeatherData(@Url urlData:String):Response<Climate>
}