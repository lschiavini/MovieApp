package com.lucas.schiavini.movieapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.lucas.schiavini.movieapp.model.ManyMoviesResponse
import com.lucas.schiavini.movieapp.model.Movie
import com.lucas.schiavini.movieapp.model.MovieDatabase
import com.lucas.schiavini.movieapp.model.MoviesAPIService
import com.lucas.schiavini.movieapp.util.SharedPreferencesHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MovieListViewModel(application: Application) : BaseViewModel(application)  {
    private var prefHelper = SharedPreferencesHelper(getApplication())

    private val secondToNanoSeconds = 1000 * 1000 * 1000L
    private var refreshTime = 5 * 60 * secondToNanoSeconds

    private val moviesService = MoviesAPIService()

    private val disposable = CompositeDisposable()

    val movies = MutableLiveData<List<Movie>>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        checkCacheDuration()
        val updateTime = prefHelper.getUpdateTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime)
            fetchFromDatabase()
        else
            fetchFromRemote()
    }

    private fun checkCacheDuration() {
        val cachePreference = prefHelper.getCacheDuration()

        try{
            val cachePreferenceInt = cachePreference?.toInt() ?: 5 * 60
            refreshTime = cachePreferenceInt.times(secondToNanoSeconds)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    private fun fetchFromDatabase() {
        loading.value = true
        launch {
            val movies = MovieDatabase(getApplication()).movieDao().getAllMovies()
            moviesRetrieved(movies)
            Toast.makeText(getApplication(), "Movies retrieved from database", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchFromRemote(){
        loading.value = true
        disposable.add(
            moviesService.getMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ManyMoviesResponse>() {
                    override fun onSuccess(manyMoviesResponse: ManyMoviesResponse) {
                        storeMoviesLocally(manyMoviesResponse.results)
                        Toast.makeText(getApplication(), "Movies retrieved from the endpoint", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        moviesLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    fun refreshBypassCache() {
        fetchFromRemote()
    }

    private fun moviesRetrieved(moviesList: List<Movie>) {
        movies.value = moviesList
        moviesLoadError.value = false
        loading.value = false
    }

    private fun storeMoviesLocally(list: List<Movie>) {
        launch {
            val dao = MovieDatabase(getApplication()).movieDao()
            dao.deleteAllMovies()

            val result = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                ++i
            }
            moviesRetrieved(list)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
