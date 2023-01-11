package com.github.alexgreench.observerpattern

interface RepositoryObserver {
    fun onDataChanged(data: String?)
}