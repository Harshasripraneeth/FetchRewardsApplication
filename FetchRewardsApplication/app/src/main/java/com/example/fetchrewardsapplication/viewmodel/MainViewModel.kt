package com.example.fetchrewardsapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardsapplication.model.APIData
import com.example.fetchrewardsapplication.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {

    private val listData: MutableLiveData<List<APIData>> by lazy {
        MutableLiveData<List<APIData>>().also {
            loadData()
        }
    }
    private fun loadData() = viewModelScope.launch {
        repository.getData().let { response ->
        if(response.isSuccessful) listData.postValue(response.body()?.filter{
            !it.name.isNullOrEmpty()
        })
        else Log.d("error","error occured!!!")
        }
    }
    fun getListData(): LiveData<List<APIData>> {
        return listData
    }
}