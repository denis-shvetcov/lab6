package com.example.lab6.task1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.R
import java.util.concurrent.Future

class ExecutorServiceActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var counting: Future<*>
    lateinit var textSecondsElapsed: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onPause() {
        super.onPause()
        Log.i("State", "State = paused")
        counting.cancel(true)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        Log.i("State", "State = resumed")

        counting = (application as SingletonApplication).getExecutor().submit {
            while (true) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds_elapsed) + secondsElapsed++
                }
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
