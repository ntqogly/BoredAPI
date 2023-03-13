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
        with(binding) {
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.start()
            tvAct.visibility = INVISIBLE
            getActButton.isEnabled = false
            val activityApi = ApiFactory.getApiService().loadActivity()
            tvAct.text = activityApi.activity
            chronometer.stop()
            getActButton.isEnabled = true
            tvAct.visibility = VISIBLE
        }
    }

    private suspend fun loadAccessibility() {
        with(binding) {
            tvAccessibility.visibility = INVISIBLE
            val activityApi = ApiFactory.getApiService().loadActivity()
            tvAccessibility.text = activityApi.accessibility.toString()
            tvAccessibility.visibility = VISIBLE
        }
    }

    private suspend fun loadType() {
        with(binding) {
            tvType.visibility = INVISIBLE
            val activityApi = ApiFactory.getApiService().loadActivity()
            tvType.text = activityApi.type
            tvType.visibility = VISIBLE
        }
    }

    private suspend fun loadParticipants() {
        with(binding) {
            tvParticipants.visibility = INVISIBLE
            val activityApi = ApiFactory.getApiService().loadActivity()
            tvParticipants.text = activityApi.participants.toString()
            tvParticipants.visibility = VISIBLE
        }
    }

    private suspend fun loadPrice() {
        with(binding) {
            tvPrice.visibility = INVISIBLE
            val activityApi = ApiFactory.getApiService().loadActivity()
            tvPrice.text = activityApi.price.toString()
            tvPrice.visibility = VISIBLE
        }
    }
}