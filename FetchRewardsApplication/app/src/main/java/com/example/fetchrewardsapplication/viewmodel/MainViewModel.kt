package com.example.fetchrewardsapplication.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.fetchrewardsapplication.model.APIData
import com.example.fetchrewardsapplication.model.GroupItemsData
import com.example.fetchrewardsapplication.model.RecyclerViewData
import com.example.fetchrewardsapplication.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 *  created by Harsha Sri Praneeth Konduru
 *  Dagger-Hilt is used to inject the [repository].
 */

private const val TAG_NAME = "MainViewModel"
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {

    private val listData: MutableLiveData<List<GroupItemsData>> by lazy {
        MutableLiveData<List<GroupItemsData>>().also{
            loadData()
        }
    }

    private val mapData: MutableLiveData<RecyclerViewData> by lazy{
        MutableLiveData<RecyclerViewData>().also{
            loadFormattedData()
        }
    }

    private fun loadFormattedData() = viewModelScope.launch {
        repository.getData().let{ response ->
            if(response.isSuccessful){
                val sortedData = response.body()
                    ?.filter { !it.name.isNullOrEmpty() }
                    ?.sortedWith(compareBy<APIData> { it.listId }.thenBy { it.name })

                val map = mutableMapOf<Int,MutableList<RecyclerViewData.ItemData>>()
                val formattedData = RecyclerViewData(map)

                sortedData?.forEach { item ->
                    if(formattedData.map.containsKey(item.listId)){
                        val tempList = formattedData.map[item.listId]
                        val tempObject = RecyclerViewData.ItemData(item.id,item.name)
                        tempList?.add(tempObject)
                        if(tempList != null)
                            formattedData.map[item.listId] = tempList
                    }
                    else{
                        val tempObject = RecyclerViewData.ItemData(item.id,item.name)
                        val tempList = mutableListOf(tempObject)
                        formattedData.map[item.listId] = tempList
                    }
                }
                mapData.postValue(formattedData)
            }
        }
    }
    private fun loadData() = viewModelScope.launch {
        repository.getData().let { response ->
        if(response.isSuccessful) {

            /***
             * Filtering the items having null or blank @property[name]
             * sorting with listId and then name of the item.
             */

          val sortedData = response.body()
                ?.filter { !it.name.isNullOrEmpty() }
                ?.sortedWith(compareBy<APIData> { it.listId }.thenBy { it.name })

            Log.d(TAG_NAME, sortedData?.size.toString())

            /***
             * combining the data into a hashmap, that can be used for grouping of recyclerview.
             */

            val map = mutableMapOf<Int,MutableList<RecyclerViewData.ItemData>>()
            val formattedData = RecyclerViewData(map)

            sortedData?.forEach { item ->
                if(formattedData.map.containsKey(item.listId)){
                    val tempList = formattedData.map[item.listId]
                    val tempObject = RecyclerViewData.ItemData(item.id,item.name)
                    tempList?.add(tempObject)
                    if(tempList != null)
                    formattedData.map[item.listId] = tempList
                }
                else{
                    val tempObject = RecyclerViewData.ItemData(item.id,item.name)
                    val tempList = mutableListOf(tempObject)
                    formattedData.map[item.listId] = tempList
                }
            }

            /***
             * Converting the map into a list of objects for the recyclerview.
             */

            val result = mutableListOf<GroupItemsData>()
            if(formattedData != null){
                for(listId in formattedData.map.keys){
                    val tempList = formattedData.map[listId]
                    if(tempList != null){
                        val tempObject = GroupItemsData(listId,tempList)
                        result.add(tempObject)
                        Log.d(TAG_NAME, tempList.size.toString())
                    }
                    else{
                        Log.d(TAG_NAME,"Data Missing at the time of conversion.")
                    }
                }
            }

            listData.postValue(result)
        }
        else Log.d(TAG_NAME,"error occured!!!")
        }
    }

    fun getListData(): LiveData<List<GroupItemsData>> {
        return listData
    }

    fun getMapData(): LiveData<RecyclerViewData> {
        return mapData
    }
}