package com.lucas.schiavini.movieapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lucas.schiavini.client.KTorSimpleClient
import com.lucas.schiavini.client.model.Movie
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieDetailViewModel(application: Application) : BaseViewModel(application) {
    val movieLiveData = MutableLiveData<Movie>()

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
                    val currentMovie = KTorSimpleClient().getMovie(id.toString())
                    movieLiveData.value = currentMovie
                } catch (e:Exception){
                    Log.e("Error AAAAAA", e.toString())
                }

            }
        }
    }


}

