package com.lucas.schiavini.movieapp.model
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

const val apiKey = "api_key=4473ae667f6dd6ef7e5ba9ac5204a9c8"

data class ManyMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

interface MoviesAPI {
    @GET("discover/movie?primary_release_year=2021&sort_by=vote_average.desc&$apiKey")
    fun getMovies(): Single<ManyMoviesResponse>

    @GET("discover/movie/{movieId}?$apiKey")
    fun getMovie(@Path("movieId", encoded = true) movieId: String): Single<Movie>

}