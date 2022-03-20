package com.example.lab6.task1

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class SingletonApplication : Application() {
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()


    fun getExecutor(): ExecutorService {
        return executor
    }
}