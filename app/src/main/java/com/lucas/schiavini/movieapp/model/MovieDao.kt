package com.lucas.schiavini.movieapp.model
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import com.lucas.schiavini.client.model.Movie
//
//@Dao
//interface MovieDao {
//    @Insert
//    suspend fun insertAll(vararg movie: Movie) : List<Long>
//
//    @Query("SELECT * FROM movie")
//    suspend fun getAllMovies() : List<Movie>
//
//    @Query("SELECT * FROM movie WHERE id=:movieId")
//    suspend fun getMovie(movieId: Int) : Movie
//
//    @Query("DELETE FROM movie")
//    suspend fun deleteAllMovies()
//}