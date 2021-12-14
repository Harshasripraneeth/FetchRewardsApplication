package com.example.fetchrewardsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.fetchrewardsapplication.databinding.ActivityMainBinding
import com.example.fetchrewardsapplication.model.APIResponse
import com.example.fetchrewardsapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding :ActivityMainBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewmodel.getListData().observe(this, Observer<APIResponse>{ response ->
            val length = response.list.size
            Log.d("data", length.toString())
            Toast.makeText(this, length.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}