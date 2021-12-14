package com.example.fetchrewardsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fetchrewardsapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * [MainActivity]
 * Screen as an entry point into the application to display list of items.
 * Navigation graph component is used to control the flow of displaying the fragments.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding :ActivityMainBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}