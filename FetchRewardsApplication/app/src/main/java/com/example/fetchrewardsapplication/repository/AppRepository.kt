package com.example.fetchrewardsapplication.repository

import com.example.fetchrewardsapplication.api.RetrofitAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val retrofitAPI: RetrofitAPI) {

    suspend fun getData() = retrofitAPI.getData()

}