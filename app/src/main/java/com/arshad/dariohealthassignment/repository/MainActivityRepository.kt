package com.arshad.dariohealthassignment.repository

import com.skymet.doordrishti.view.data.api.ApiHelper

class MainActivityRepository(private val apiHelper: ApiHelper) {

    suspend fun getRepositories(authKey: String, searchKey: String) = apiHelper.getRepositories(authKey, searchKey)
}