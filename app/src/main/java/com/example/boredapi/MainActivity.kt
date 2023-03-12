package com.example.boredapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.lifecycleScope
import com.example.boredapi.databinding.ActivityMainBinding
import com.example.boredapi.model.ApiFactory
import kotlinx.coroutines.launch

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
        binding.tvActToDo.visibility = INVISIBLE
        binding.getActButton.isEnabled = false
        val activity = ApiFactory.getApiService().loadActivity()
        binding.tvActToDo.text = activity.activity

        binding.chronometer.stop()
        binding.getActButton.isEnabled = true
        binding.tvActToDo.visibility = VISIBLE
    }

}