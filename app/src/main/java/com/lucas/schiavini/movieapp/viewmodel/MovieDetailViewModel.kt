package com.lucas.schiavini.movieapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.lucas.schiavini.movieapp.model.Movie
import com.lucas.schiavini.movieapp.model.MovieDatabase
import com.lucas.schiavini.movieapp.model.MoviesAPIService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailViewModel(application: Application) : BaseViewModel(application) {
    val movieLiveData = MutableLiveData<Movie>()
    private val disposable = CompositeDisposable()
    private val moviesService = MoviesAPIService()

    fun fetch(id: Int) {
        launch {
            val movie = MovieDatabase(getApplication()).movieDao().getMovie(id)
            movieLiveData.value = movie
        }
    }

    fun fetchMovieFromRemote(id: Int) {
//        loading.value = true
        disposable.add(
            moviesService.getMovie(id.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<JsonObject>() {
                    override fun onSuccess(movie: JsonObject) {
                        val newMovie = Movie(
                            title = (movie["title"] as JsonPrimitive).asString,
                            id = (movie["id"] as JsonPrimitive).asString,
                            releaseDate = (movie["release_date"] as JsonPrimitive).asString,
                            duration = (movie["runtime"] as JsonPrimitive).asString,
                            director = getDirectorFromCredits(movie),
                            description = (movie["overview"] as JsonPrimitive).asString,
                            score = (movie["vote_average"] as JsonPrimitive).asString,
                            imageUrl = (movie["poster_path"] as JsonPrimitive).asString,
                        )
                        movieLiveData.value = newMovie
                        Toast.makeText(getApplication(), "Movie retrieved", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
//                        moviesLoadError.value = true
//                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    fun getDirectorFromCredits(creditsJsonObject: JsonObject) : String {
        try{
            val credits = creditsJsonObject["credits"]
            val crew: JsonArray = (credits as JsonObject)["crew"] as JsonArray
            var director = ""
            for(i in 0 until crew.size()) {
                val currCrew: JsonObject = crew[i] as JsonObject
                val currJob = currCrew["job"].asString
                if(currJob == "Director") {
                    director = currCrew["name"].asString
                    break
                }
            }
            return director
        } catch(e: Exception) {
            return ""
        }
    }

}

