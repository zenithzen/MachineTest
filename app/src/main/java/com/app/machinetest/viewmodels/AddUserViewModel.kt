package com.app.machinetest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.machinetest.localdatabaseservice.entities.UserEntity
import com.app.machinetest.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddUserViewModel@Inject constructor(
    private val mainRepository: AppRepository,
) : ViewModel() {
    private val _insertUserData = MutableLiveData<Long>()
    val insertUserData: LiveData<Long>
        get() = _insertUserData

    //For Room Insertion
    fun insertUserData(userEntity: UserEntity) = viewModelScope.launch {
        _insertUserData.postValue(mainRepository.insertUserData(userEntity))
    }

}
