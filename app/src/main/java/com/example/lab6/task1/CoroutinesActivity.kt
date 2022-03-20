package com.example.lab6.task1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.R
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0

    private lateinit var textSecondsElapsed: TextView
    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onPause() {
        super.onPause()
        Log.i("State", "State = paused")
        job.cancel()
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        Log.i("State", "State = resumed")
        job = GlobalScope.launch(Dispatchers.Main) {
            while (isActive) {
                delay(1000)
                textSecondsElapsed.text = getString(R.string.seconds_elapsed) + secondsElapsed++
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putInt("SECONDS_ELAPSED", secondsElapsed)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run {
            secondsElapsed = getInt("SECONDS_ELAPSED")
        }
    }
}
