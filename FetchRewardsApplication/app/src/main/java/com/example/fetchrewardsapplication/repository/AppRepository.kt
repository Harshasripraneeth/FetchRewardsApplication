package com.example.fetchrewardsapplication.repository

import com.example.fetchrewardsapplication.api.RetrofitAPI
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Harsha Sri Praneeth.
 * class to maintain and get data from the API calls.
 */
@Singleton
class AppRepository @Inject constructor(private val retrofitAPI: RetrofitAPI) {

    suspend fun getData() = retrofitAPI.getData()

}