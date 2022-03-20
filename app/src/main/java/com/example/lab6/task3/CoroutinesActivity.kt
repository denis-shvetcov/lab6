package com.example.lab6.task3

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.R
import com.example.lab6.task2.ExecutorServiceActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URL

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var downloading: Job
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.downloading_image)
        image = findViewById(R.id.imageView)
        downloading = GlobalScope.launch(Dispatchers.IO) {
            val url = URL(ExecutorServiceActivity.LINK)
            val icon = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            GlobalScope.launch(Dispatchers.Main) {
                image.setImageBitmap(icon)
            }
        }
    }
}