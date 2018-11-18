package com.github.alexgreench.observerpattern;

import android.annotation.SuppressLint;
import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataRepository implements Subject{

    private ArrayList<RepositoryObserver> observers;
    private String data;

    private DataRepository() {
        observers = new ArrayList<>();
        getData();
    }

    private static DataRepository INSTANCE = null;

    public static DataRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataRepository();
        }
        return INSTANCE;
    }

    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String currentDateandTime = sdf.format(new Date());
                setData(currentDateandTime);
                getData();
            }
        }, 250);
    }

    private void setData(String data) {
        this.data = data;
        handleEvent();
    }

    @Override
    public void doSubscribe(RepositoryObserver observer) {
        if(!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void doUnsubscribe(RepositoryObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void handleEvent() {
        for (RepositoryObserver observer: observers) {
            observer.onDataChanged(data);
        }
    }
}