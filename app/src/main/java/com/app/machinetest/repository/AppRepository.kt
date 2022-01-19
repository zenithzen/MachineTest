package com.app.machinetest.repository

import com.apollographql.apollo.ApolloClient

import com.app.machinetest.baseresult.safeApiCall
import com.app.machinetest.localdatabaseservice.AppLocalRoomDatabaseDao
import com.app.machinetest.localdatabaseservice.entities.UserEntity
import com.app.machinetest.models.Climate
import com.app.machinetest.remoteservice.ApiHelper
import retrofit2.Response
import retrofit2.http.Url
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val appLocalRoomDatabaseDao: AppLocalRoomDatabaseDao,
    private val apolloClient: ApolloClient,

    ) {

    //retrofit
    suspend fun getWeatherData(urlData: String) = safeApiCall { apiHelper.getWeatherData(urlData) }

    //for room DataBase
    suspend fun insertUserData(user: UserEntity) = appLocalRoomDatabaseDao.insert(user)
    suspend fun fetchUsers() = appLocalRoomDatabaseDao.fetch()
    suspend fun clearDb() = appLocalRoomDatabaseDao.clearDb()
    suspend fun deleteItem(item: UserEntity) = appLocalRoomDatabaseDao.delete(item)



}