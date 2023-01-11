package com.github.alexgreench.observerpattern

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import java.text.SimpleDateFormat
import java.util.*

class DataRepository private constructor() : Subject {

    private val observers: ArrayList<RepositoryObserver> = ArrayList()
    private var data: String? = null

    init {
        getData()
    }

    private fun getData() {
        Handler(Looper.getMainLooper()).postDelayed({
            @SuppressLint("SimpleDateFormat") val sdf = SimpleDateFormat("HH:mm:ss")
            val currentTime = sdf.format(Date())
            setData(currentTime)
            getData()
        }, 250)
    }

    private fun setData(data: String) {
        this.data = data
        handleEvent()
    }

    override fun doSubscribe(observer: RepositoryObserver?) {
        if (!observers.contains(observer)) {
            if (observer != null) {
                observers.add(observer)
            }
        }
    }

    override fun doUnsubscribe(observer: RepositoryObserver?) {
        observers.remove(observer)
    }

    override fun handleEvent() {
        for (observer in observers) {
            observer.onDataChanged(data)
        }
    }

    companion object {
        val instance: DataRepository by lazy {
            DataRepository()
        }
    }
}