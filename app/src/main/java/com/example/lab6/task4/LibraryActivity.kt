package com.example.lab6.task4

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.R
import com.example.lab6.task2.ExecutorServiceActivity
import com.squareup.picasso.Picasso

class LibraryActivity : AppCompatActivity() {
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.downloading_image)
        image = findViewById(R.id.imageView)
        Picasso.get().load(ExecutorServiceActivity.LINK).into(image);
    }
}