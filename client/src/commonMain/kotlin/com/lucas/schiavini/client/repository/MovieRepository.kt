package com.lucas.schiavini.client.repository

import com.lucas.schiavini.client.ApplicationDispatcher
import com.lucas.schiavini.client.KTorSimpleClient
import com.lucas.schiavini.client.db.MovieDatabase
import com.lucas.schiavini.client.model.Movie
import com.lucas.schiavini.client.model.MovieListResult
import com.lucas.schiavini.client.model.MovieResult
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface MovieRepositoryInterface {
    fun fetchMovieAsFlow(): Flow<Movie>
    fun fetchMoviesAsFlow(): Flow<MovieListResult>
    suspend fun fetchMovie(id: String): Movie
    suspend fun fetchMovies(): MovieListResult
    suspend fun fetchAndStoreMovie(id: String) : Movie
    suspend fun fetchAndStoreMovies() : MovieListResult
    suspend fun storeMoviesLocally(movieResults: List<MovieResult>)
    suspend fun storeSingleMovieLocally(movie: Movie)
    suspend fun deleteAllMovies()
}

fun createRepository(driverFactory: DriverFactory): MovieRepository {
    val driver = driverFactory.createDriver()
    val database = MovieDatabase(driver)
    return MovieRepository(database)
}

@OptIn(DelicateCoroutinesApi::class)
class MovieRepository(
    private val database: MovieDatabase
): MovieRepositoryInterface {

    private val moviesApi: KTorSimpleClient = KTorSimpleClient()


    override fun fetchMovieAsFlow(): Flow<Movie> {
        TODO("Not yet implemented")
    }

    override fun fetchMoviesAsFlow(): Flow<MovieListResult> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchMovie(id: String): Movie {
        return moviesApi.getMovie(id)
    }

    override suspend fun fetchMovies(): MovieListResult {
        return moviesApi.getMovies()
    }

    override suspend fun fetchAndStoreMovie(id: String) : Movie {
        val movie = moviesApi.getMovie(id)
        storeSingleMovieLocally(movie)
        return movie
    }

    override suspend fun storeSingleMovieLocally(movie: Movie) {
        database.moviesDBQueries.insertItem(
            movie.id.toLong(),
            movie.originalTitle,
            movie.title,
            movie.overview,
            movie.posterPath,
            movie.releaseDate,
            movie.voteAverage.toString(),
            movie.director
        )
    }

    override suspend fun fetchAndStoreMovies(): MovieListResult {
//        val movieResults = moviesApi.getMovies()
        val movieResults = moviesApi.mockGetMovies()
        storeMoviesLocally(movieResults.movieResults)
        return movieResults
    }

    override suspend fun storeMoviesLocally(movieResults: List<MovieResult>) {
        movieResults.forEach {movie ->
            database.moviesDBQueries.insertItem(
                movie.id.toLong(),
                movie.originalTitle,
                movie.title,
                movie.overview,
                movie.posterPath,
                movie.releaseDate,
                movie.voteAverage.toString(),
                ""
            )
        }
    }

    override suspend fun deleteAllMovies() {
        database.moviesDBQueries.deleteAll()
    }
}