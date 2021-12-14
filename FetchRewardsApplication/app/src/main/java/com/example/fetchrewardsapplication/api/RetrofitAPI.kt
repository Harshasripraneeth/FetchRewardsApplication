package com.example.fetchrewardsapplication.api

import com.example.fetchrewardsapplication.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPI {

    companion object{
       // const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
       const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com"
    }

    @GET("/hiring.json")
    suspend fun getData(): APIResponse
}