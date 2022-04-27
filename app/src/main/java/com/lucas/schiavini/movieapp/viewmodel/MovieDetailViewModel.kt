package com.lucas.schiavini.movieapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lucas.schiavini.client.model.Movie
import com.lucas.schiavini.client.repository.MovieRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieDetailViewModel(application: Application) : BaseViewModel(application) {
    val movieLiveData = MutableLiveData<Movie>()
    var repository: MovieRepository? = null

    fun fetchMovie(id: Int) {
        fetchFromDatabase(id)
        if(movieLiveData.value?.director == null) {
            fetchMovieFromRemote(id)
        }
    }

    private fun fetchFromDatabase(id: Int) {
        launch {
            val movie = repository?.fetchMovieDB(id.toString())?.also {
                setMovieLiveData(it)
            }
        }
    }

    private fun fetchMovieFromRemote(id: Int) {
        runBlocking {
            launch {
                try{
                    repository?.fetchAndStoreMovie(id.toString())?.also {
                        setMovieLiveData(it)
                    }
                } catch (e:Exception){
                    Log.e("Error", e.toString())
                }
            }
        }
    }

    private fun setMovieLiveData(movie: Movie) {
        movieLiveData.value = movie
    }

}

