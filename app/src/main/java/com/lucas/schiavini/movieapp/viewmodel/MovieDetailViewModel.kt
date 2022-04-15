package com.lucas.schiavini.movieapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lucas.schiavini.client.model.Movie
import com.lucas.schiavini.client.repository.MovieRepository
import com.lucas.schiavini.movieapp.AndroidRepository
import com.lucas.schiavini.movieapp.view.MovieDetailFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieDetailViewModel(application: Application) : BaseViewModel(application) {
    val movieLiveData = MutableLiveData<Movie>()
    var repository: MovieRepository? = null

//    fun fetch(id: Int) {
//        launch {
//            val movie = MovieDatabase(getApplication()).movieDao().getMovie(id)
//            movieLiveData.value = movie
//        }
//    }

    fun fetchMovieFromRemote(id: Int) {
        runBlocking {
            launch {
                try{
                    val currentMovie = repository?.fetchAndStoreMovie(id.toString())?.also {
                        movieLiveData.value = it
                    }
                } catch (e:Exception){
                    Log.e("Error AAAAAA", e.toString())
                }

            }
        }
    }


}

