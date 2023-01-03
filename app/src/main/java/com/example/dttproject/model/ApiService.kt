package com.example.dttproject.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
//retrofit call
    @Headers("Access-Key")
    @GET ("api/house")
    suspend fun getHouses(): List<HouseItem>

    companion object {
         private var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("URL")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService :: class.java)
            }
            return apiService!!
        }
    }
}