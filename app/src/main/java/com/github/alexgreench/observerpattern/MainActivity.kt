package com.github.alexgreench.observerpattern

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), RepositoryObserver {

    private var dataRepository: Subject = DataRepository.instance

    lateinit var textSubscriber: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textSubscriber = findViewById(R.id.text_subscriber)

        findViewById<View>(R.id.btn_subscribe).setOnClickListener {
            dataRepository.doSubscribe(this@MainActivity)
        }
        findViewById<View>(R.id.btn_unsubscribe).setOnClickListener {
            dataRepository.doUnsubscribe(this@MainActivity)
            textSubscriber.setText(R.string.you_are_unsubscribed)
        }
    }

    override fun onDataChanged(data: String?) {
        textSubscriber.text = data
    }

    override fun onDestroy() {
        super.onDestroy()
        dataRepository.doUnsubscribe(this)
    }
}