package com.example.lab6.task1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.R

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0

    lateinit var textSecondsElapsed: TextView
    lateinit var backgroundThread: Thread


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onPause() {
        super.onPause()
        Log.i("State", "State = paused")
        backgroundThread.interrupt()
    }

    override fun onResume() {
        super.onResume()
        Log.i("State", "State = resumed")
        backgroundThread = thread()
        backgroundThread.start()
    }


    @SuppressLint("SetTextI18n")
    private fun thread() = Thread {
        Thread.currentThread().name = "Thread " + Thread.currentThread().id
        try {
            while (Thread.currentThread().isInterrupted) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds_elapsed) + secondsElapsed++
                }
            }
        } catch (e: InterruptedException) {
            Log.i("Thread", "Thread was interrupted")
            return@Thread
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
