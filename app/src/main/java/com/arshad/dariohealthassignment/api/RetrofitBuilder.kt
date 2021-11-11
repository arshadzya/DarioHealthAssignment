package com.skymet.doordrishti.view.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    //https://www.omdbapi.com/?apikey=9278e7bc&s=humsafar
    private const val BASE_URL = "https://www.omdbapi.com/"

    var gson = GsonBuilder()
        .setLenient()
        .create()!!

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}