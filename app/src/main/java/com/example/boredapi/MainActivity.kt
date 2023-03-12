package com.example.boredapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.lifecycleScope
import com.example.boredapi.databinding.ActivityMainBinding
import com.example.boredapi.network.ApiFactory
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getActButton.setOnClickListener {
            lifecycleScope.launch {
                load()
            }
        }
    }

    private suspend fun load() {
        binding.chronometer.base = SystemClock.elapsedRealtime()
        binding.chronometer.start()
        binding.tvAct.visibility = INVISIBLE
        binding.tvType.visibility = INVISIBLE
        binding.getActButton.isEnabled = false
        val activityApi = ApiFactory.getApiService().loadActivity()
        binding.tvAct.text = activityApi.activity
//        val typeApi = ApiFactory.getApiService().loadActivityType()
//        binding.tvType.text = typeApi.type
        binding.chronometer.stop()
        binding.getActButton.isEnabled = true
        binding.tvAct.visibility = VISIBLE
//        binding.tvType.visibility = VISIBLE
    }

}