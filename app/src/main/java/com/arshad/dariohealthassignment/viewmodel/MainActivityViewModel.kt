package com.arshad.dariohealthassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arshad.dariohealthassignment.data.MainActivityState
import com.arshad.dariohealthassignment.repository.MainActivityRepository
import com.skymet.doordrishti.view.Resource

import kotlinx.coroutines.Dispatchers

class MainActivityViewModel(private val mainActivityRepository: MainActivityRepository) : ViewModel() {

    private val _mainActivityStatus = MutableLiveData<MainActivityState>()
    val dashBoardState: LiveData<MainActivityState> = _mainActivityStatus

    fun getRepositories(authKey: String, searchKey: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainActivityRepository.getRepositories( authKey,  searchKey)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }



}