package com.example.fetchrewardsapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchrewardsapplication.R
import com.example.fetchrewardsapplication.adapters.ItemAdapter
import com.example.fetchrewardsapplication.databinding.FragmentMainActivityBinding
import com.example.fetchrewardsapplication.model.GroupItemsData
import com.example.fetchrewardsapplication.adapters.GroupItemsAdapter
import com.example.fetchrewardsapplication.model.RecyclerViewData
import com.example.fetchrewardsapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/***
 * created by Harsha Sri Praneeth
 * Starting fragment of the Navigation graph.
 */
private const val TAG_NAME = "MainActivityFragment"

@AndroidEntryPoint
class MainActivityFragment: Fragment(R.layout.fragment_main_activity){
    private val viewmodel by viewModels<MainViewModel>()

    private var _binding : FragmentMainActivityBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainActivityBinding.bind(view)

        val recyclerViewAdapter = GroupItemsAdapter(object:GroupItemsAdapter.CustomItemClickListener{
            override fun onItemClick(data: List<RecyclerViewData.ItemData>) {

                if(!binding.rcItemView.isVisible) binding.rcItemView.visibility = View.VISIBLE
                val itemAdapter = ItemAdapter(data)
                binding.rcItemView.apply {
                    adapter = itemAdapter
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                }
            }
        })

        binding.rcGroupItemsView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = recyclerViewAdapter
        }

        /***
         * observes the livedata from the viewmodel.
         * updates to the group recycler view whenever the data is updated/changed.
         */

        viewmodel.getListData().observe(viewLifecycleOwner, Observer<List<GroupItemsData>>{ data ->
            recyclerViewAdapter.list = data
            Log.i(TAG_NAME,data.toString())
        })



    }
}