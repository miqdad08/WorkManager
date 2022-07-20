package com.example.workmanagerdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmanagerdemo1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val KEY_COUNT_VALUE = "key_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("LIFECYCLE", "on create di panggil")

        binding.button.setOnClickListener {

        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "on start di panggil")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "on resume di panggil")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "on pause di panggil")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "on stop di panggil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "on destroy di panggil")
    }

    private fun setOneTimeWorkRequest() {

    }
}

