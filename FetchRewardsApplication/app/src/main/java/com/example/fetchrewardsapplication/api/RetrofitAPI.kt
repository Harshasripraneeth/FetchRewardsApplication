package com.example.fetchrewardsapplication.api

import com.example.fetchrewardsapplication.model.APIData
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Harsha Sri Praneeth.
 */
interface RetrofitAPI {

    companion object{
       const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com"
    }

    /***
     *  Get the list of items
     *  [APIData] is the data class for the item retrieved.
     */
    @GET("/hiring.json")
    suspend fun getData(): Response<List<APIData>>
}