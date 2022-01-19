package com.app.machinetest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.machinetest.localdatabaseservice.entities.UserEntity
import com.app.machinetest.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewMode @Inject constructor(
    private val mainRepository: AppRepository,
) : ViewModel() {


    private val _deleteLiveData = MutableLiveData<Int>()
    val deleteLiveData: LiveData<Int>
        get() = _deleteLiveData

    private val _getAllUserData = MutableLiveData<List<UserEntity>>()
    val getAllUserData: LiveData<List<UserEntity>>
        get() = _getAllUserData

    fun getAllUsersData() = viewModelScope.launch {

        println("@CALLED" + "YEs")
        _getAllUserData.postValue(mainRepository.fetchUsers())
    }

    fun deleteItem(item: UserEntity) = viewModelScope.launch {
        _deleteLiveData.postValue(mainRepository?.deleteItem(item))

    }

    fun updateLiveData(list: List<UserEntity>) {
        _getAllUserData.postValue(list)
    }


}