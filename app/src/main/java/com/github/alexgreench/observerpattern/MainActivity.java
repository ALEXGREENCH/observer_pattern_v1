package com.github.alexgreench.observerpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RepositoryObserver{

    private Subject dataRepository;
    private TextView textSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataRepository = DataRepository.getInstance();

        textSubscriber = findViewById(R.id.text_subscriber);

        findViewById(R.id.btn_subscribe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataRepository.doSubscribe(MainActivity.this);
            }
        });

        findViewById(R.id.btn_unsubscribe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataRepository.doUnsubscribe(MainActivity.this);
                textSubscriber.setText(R.string.you_are_unsubscribed);
            }
        });

    }

    @Override
    public void onDataChanged(String data) {
        textSubscriber.setText(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataRepository.doUnsubscribe(this);
    }
}
