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
            setOneTimeWorkRequest()
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
        val workManager = WorkManager.getInstance(applicationContext)

        //constraint digunakan untuk syarat worker akan berjalan apabila
        //constraint terpenuhi
        val constraint = Constraints.Builder()
        //bisa berjalan jika HP dalam keadan ngecharge dan terhubung internet
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        //membuat input ke dalam worker UploadWorker.kt
        val data = Data.Builder()
            .putInt(KEY_COUNT_VALUE, 125)
            .build()

        //membuat work request
        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraint)
            .setInputData(data)
            .build()

        //mengirimkan workrequest ke sistem
        workManager.enqueue(uploadRequest)

        //mendapatkan status work manager
        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this, {
                binding.status.text = it.state.name
                if (it.state.isFinished){
                    val data = it.outputData
                    val message = data.getString(UploadWorker.KEY_WORKER)
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}

