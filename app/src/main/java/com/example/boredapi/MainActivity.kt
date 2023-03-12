package com.example.boredapi

import android.os.Bundle
import android.os.SystemClock
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.boredapi.databinding.ActivityMainBinding
import com.example.boredapi.network.ApiFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getActButton.setOnClickListener {
            lifecycleScope.launch {
                loadActivity()
            }
            lifecycleScope.launch {
                loadAccessibility()
            }
            lifecycleScope.launch {
                loadType()
            }
            lifecycleScope.launch {
                loadParticipants()
            }
            lifecycleScope.launch {
                loadPrice()
            }
        }
    }

    private suspend fun loadActivity() {
        binding.chronometer.base = SystemClock.elapsedRealtime()
        binding.chronometer.start()
        binding.tvAct.visibility = INVISIBLE
        binding.getActButton.isEnabled = false
        val activityApi = ApiFactory.getApiService().loadActivity()
        binding.tvAct.text = activityApi.activity
        binding.chronometer.stop()
        binding.getActButton.isEnabled = true
        binding.tvAct.visibility = VISIBLE

    }

    private suspend fun loadAccessibility() {
        binding.tvAccessibility.visibility = INVISIBLE
        val activityApi = ApiFactory.getApiService().loadActivity()
        binding.tvAccessibility.text = activityApi.accessibility.toString()
        binding.tvAccessibility.visibility = VISIBLE
    }

    private suspend fun loadType() {
        binding.tvType.visibility = INVISIBLE
        val activityApi = ApiFactory.getApiService().loadActivity()
        binding.tvType.text = activityApi.type
        binding.tvType.visibility = VISIBLE
    }

    private suspend fun loadParticipants() {
        binding.tvParticipants.visibility = INVISIBLE
        val activityApi = ApiFactory.getApiService().loadActivity()
        binding.tvParticipants.text = activityApi.participants.toString()
        binding.tvParticipants.visibility = VISIBLE
    }

    private suspend fun loadPrice() {
        binding.tvPrice.visibility = INVISIBLE
        val activityApi = ApiFactory.getApiService().loadActivity()
        binding.tvPrice.text = activityApi.price.toString()
        binding.tvPrice.visibility = VISIBLE
    }
}