package com.lucas.schiavini.movieapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.lucas.schiavini.client.model.Movie
import com.lucas.schiavini.client.model.MovieResult
import com.lucas.schiavini.client.repository.MovieRepository
import com.lucas.schiavini.movieapp.util.SharedPreferencesHelper
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieListViewModel(application: Application) : BaseViewModel(application)  {
    private var prefHelper = SharedPreferencesHelper(getApplication())

    lateinit var repository : MovieRepository
    private val secondToNanoSeconds = 1000 * 1000 * 1000L
    private var refreshTime = 5 * 60 * secondToNanoSeconds

    private val disposable = CompositeDisposable()

    val movies = MutableLiveData<List<Movie>>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
//        fetchFromRemote()
        fetchFromDatabase()
    }

    private fun fetchFromDatabase() {
        launch {
            repository.fetchMoviesDB().also {
                moviesRetrieved(it)
            }
        }
    }

    private fun fetchFromRemote() {
        runBlocking {
            launch {
                try{
                    val listOfMovies = repository.fetchAndStoreMovies()
                    deleteAndStoreMoviesLocally(listOfMovies)
                    Toast.makeText(getApplication(), "Movies retrieved from the endpoint", Toast.LENGTH_SHORT).show()
                } catch (e:Exception){
                    moviesLoadError.value = true
                    loading.value = false
                    e.printStackTrace()
                }
            }
        }
    }

    fun refreshBypassCache() {
        fetchFromRemote()
    }

    private fun moviesRetrieved(moviesList: List<Movie>) {
        movies.value = moviesList
        moviesLoadError.value = false
        loading.value = false
    }

    private suspend fun deleteAndStoreMoviesLocally(list: List<Movie>) {
        repository.apply {
            deleteAllMovies()
            storeMoviesLocally(list)
        }
        moviesRetrieved(list)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
