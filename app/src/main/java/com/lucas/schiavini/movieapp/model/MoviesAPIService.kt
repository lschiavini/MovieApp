package com.lucas.schiavini.movieapp.model

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MoviesAPIService {
    private val API_KEY_MOVIEDB = "4473ae667f6dd6ef7e5ba9ac5204a9c8"
    private val BASE_URL_MOVIEDB = "https://api.themoviedb.org/3/"
    private val READING_TOKEN_API_MOVIEDB = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NDczYWU2NjdmNmRkNmVmN2U1YmE5YWM1MjA0YTljOCIsInN1YiI6IjYyMGI5OTkyYzM1MTRjMDA0MzJlMWRlNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7bqq4uJZL-rC7wlZEIz5YPyfizhZYPLjth_UxVy2YCI"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL_MOVIEDB)
        .addConverterFactory(GsonConverterFactory.create()) // Converts JSON to GSON
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // creates Single from GSON
        .build()
        .create(MoviesAPI::class.java)

    fun getMovies() : Single<ManyMoviesResponse> {
        return api.getMovies()
    }

    fun getMovie(id: String) : Single<JsonObject> {
        return api.getMovie(id)
    }
}