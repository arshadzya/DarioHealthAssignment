package com.arshad.dariohealthassignment.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arshad.dariohealthassignment.repository.MainActivityRepository
import com.arshad.dariohealthassignment.viewmodel.MainActivityViewModel
import com.skymet.doordrishti.view.data.api.ApiHelper

class MainActivityViewModelFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(MainActivityRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}