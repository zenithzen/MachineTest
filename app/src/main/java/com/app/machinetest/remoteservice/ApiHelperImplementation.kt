package com.app.machinetest.remoteservice

import com.app.machinetest.models.Climate
import com.app.machinetest.models.TestApiResponseModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImplementation @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getPosts(): Response<List<TestApiResponseModel>> = apiService.getPosts()
    override suspend fun getWeatherData(urlData: String): Response<Climate> =apiService.getWeatherData(urlData)
}