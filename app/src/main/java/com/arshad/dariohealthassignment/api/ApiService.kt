package com.skymet.doordrishti.view.data.api


import MovieListModel
import retrofit2.http.*


interface ApiService {

    @GET("?")
    suspend fun getRepositories(@Query("apikey") authKey: String, @Query("s") searchKey: String): MovieListModel

}