package com.app.machinetest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.app.machinetest.baseresult.BaseResult
import com.app.machinetest.baseresult.ResultWrapper
import com.app.machinetest.localdatabaseservice.entities.UserEntity
import com.app.machinetest.models.Climate
import com.app.machinetest.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: AppRepository,
) : ViewModel() {

    private val _getWeatherData = MutableLiveData<BaseResult<Climate>>()
    val getWeatherData: LiveData<BaseResult<Climate>>
        get() = _getWeatherData

    fun clearDb() = viewModelScope.launch {
        withContext(Dispatchers.IO)
        {
            mainRepository.clearDb()
        }

    }
    fun getWeatherData(urlData: String) {
        viewModelScope.launch {
            _getWeatherData.postValue(BaseResult.loading(null))
            when (val responses = mainRepository.getWeatherData(urlData)) {
                is ResultWrapper.Success -> {
                    _getWeatherData.postValue(BaseResult.success(responses.data))
                }
                is ResultWrapper.Failure -> {
                    _getWeatherData.postValue(BaseResult.error(responses.message))
                }
            }
        }
    }


}