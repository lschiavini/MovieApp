package com.lucas.schiavini.movieapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lucas.schiavini.movieapp.model.Movie
import com.lucas.schiavini.movieapp.model.MovieDatabase
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : BaseViewModel(application) {
    val movieLiveData = MutableLiveData<Movie>()

    fun fetch(id: Int) {
        launch {
            val movie = MovieDatabase(getApplication()).movieDao().getMovie(id)
            movieLiveData.value = movie
        }
    }

}