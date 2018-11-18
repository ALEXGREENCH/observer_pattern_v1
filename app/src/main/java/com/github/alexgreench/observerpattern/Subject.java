package com.github.alexgreench.observerpattern;

public interface Subject {
    void doSubscribe(RepositoryObserver observer);
    void doUnsubscribe(RepositoryObserver observer);
    void handleEvent();
}
