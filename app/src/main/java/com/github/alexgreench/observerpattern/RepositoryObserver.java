package com.github.alexgreench.observerpattern;

public interface RepositoryObserver {
    void onDataChanged(String data);
}
