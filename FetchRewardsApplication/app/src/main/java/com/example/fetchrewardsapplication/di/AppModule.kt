package com.example.fetchrewardsapplication.di

import com.example.fetchrewardsapplication.api.RetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/***
 *  created by Harsha Sri Praneeth
 *  Interfaces to provide instances for Retrofit.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(RetrofitAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitAPI(retrofit: Retrofit): RetrofitAPI{
        return retrofit.create(RetrofitAPI::class.java)
    }
}