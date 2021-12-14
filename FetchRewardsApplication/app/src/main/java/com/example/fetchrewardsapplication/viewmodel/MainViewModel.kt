package com.example.fetchrewardsapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardsapplication.model.APIResponse
import com.example.fetchrewardsapplication.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {
    
    private val listData: MutableLiveData<APIResponse> by lazy {
        MutableLiveData<APIResponse>().also {
            loadData()
        }
    }
    private fun loadData() = viewModelScope.launch {
        listData.postValue(repository.getData())
    }
    fun getListData(): LiveData<APIResponse> {
        return listData
    }
}