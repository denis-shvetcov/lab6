package com.example.lab6.task2

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.R
import com.example.lab6.task1.SingletonApplication
import java.net.URL
import java.util.concurrent.Future


class ExecutorServiceActivity : AppCompatActivity() {
    companion object {
        const val LINK: String =
            "https://cdn.pixabay.com/photo/2019/09/24/21/07/flowers-4502219_960_720.jpg"
    }

    private lateinit var downloading: Future<*>
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.downloading_image)
        image = findViewById(R.id.imageView)
        downloading = (application as SingletonApplication).getExecutor().submit {
            val url = URL(LINK)
            val icon = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            image.post {
                image.setImageBitmap(icon)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        downloading.cancel(true)
    }
}