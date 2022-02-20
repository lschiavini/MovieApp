package com.lucas.schiavini.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lucas.schiavini.movieapp.model.Movie
import com.lucas.schiavini.movieapp.util.SharedPreferencesHelper

class MovieListViewModel(application: Application) : BaseViewModel(application)  {
    private var prefHelper = SharedPreferencesHelper(getApplication())

    private val secondToNanoSeconds = 1000 * 1000 * 1000L
    private var refreshTime = 5 * 60 * secondToNanoSeconds

    val movies = MutableLiveData<List<Movie>>()
    val movieLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        checkCacheDuration()
        val updateTime = prefHelper.getUpdateTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime)
            fetchFromDatabase()
        else
            fetchFromRemote()
    }

    fun checkCacheDuration() {

    }

    fun fetchFromDatabase() {

    }

    fun fetchFromRemote(){

    }

}
