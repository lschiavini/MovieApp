package com.lucas.schiavini.client.repository

import com.lucas.schiavini.client.KTorSimpleClient
import com.lucas.schiavini.client.db.MovieDatabase
import com.lucas.schiavini.client.model.Movie
import com.lucas.schiavini.client.model.MovieListResult
import com.lucas.schiavini.client.utils.toMovie
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryInterface {
    fun fetchMovieAsFlow(): Flow<Movie>
    fun fetchMoviesAsFlow(): Flow<MovieListResult>
    suspend fun fetchMovie(id: String): Movie
    suspend fun fetchMovies(): List<Movie>
    suspend fun fetchMovieDB(id: String): Movie
    suspend fun fetchMoviesDB(): List<Movie>
    suspend fun fetchAndStoreMovie(id: String) : Movie
    suspend fun fetchAndStoreMovies() : List<Movie>
    suspend fun storeMoviesLocally(movies: List<Movie>)
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
        val movie = moviesApi.getMovie(id).toMovie()
        return movie
    }

    override suspend fun fetchMovies(): List<Movie> {
        //        val movieResults = moviesApi.getMovies()
        val movieResults = moviesApi.mockGetMovies()
        return movieResults.movieResults.map {
            it.toMovie()
        }
    }

    override suspend fun fetchMovieDB(id: String): Movie {
        return database.moviesDBQueries.selectById(id.toLong()).executeAsOne().toMovie()
    }

    override suspend fun fetchMoviesDB(): List<Movie> {
        val movieList = database.moviesDBQueries.selectAll().executeAsList().map {
            it.toMovie()
        }
        return movieList
    }

    override suspend fun fetchAndStoreMovie(id: String) : Movie {
        val movie = fetchMovie(id)
        storeSingleMovieLocally(movie)
        return movie
    }

    override suspend fun storeSingleMovieLocally(movie: Movie) {
        database.moviesDBQueries.insertItem(
            movie.id,
            movie.originalTitle,
            movie.title,
            movie.overview,
            movie.posterPath,
            movie.releaseDate,
            movie.voteAverage.toString(),
            movie.director,
            movie.runtime
        )
    }

    override suspend fun fetchAndStoreMovies(): List<Movie> {
        val movieList = fetchMovies()
        storeMoviesLocally(movieList)
        return movieList
    }

    override suspend fun storeMoviesLocally(movies: List<Movie>) {
        movies.forEach {movie ->
            database.moviesDBQueries.insertItem(
                movie.id,
                movie.originalTitle,
                movie.title,
                movie.overview,
                movie.posterPath,
                movie.releaseDate,
                movie.voteAverage.toString(),
                "",
                runtime = null
            )
        }
    }

    override suspend fun deleteAllMovies() {
        database.moviesDBQueries.deleteAll()
    }
}