package com.github.alexgreench.observerpattern

interface Subject {
    fun doSubscribe(observer: RepositoryObserver?)
    fun doUnsubscribe(observer: RepositoryObserver?)
    fun handleEvent()
}