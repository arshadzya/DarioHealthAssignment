package com.skymet.doordrishti.view.data.api



class ApiHelper(private val apiService: ApiService) {

    suspend fun getRepositories(authKey: String, searchKey: String) = apiService.getRepositories(authKey, searchKey)
  }