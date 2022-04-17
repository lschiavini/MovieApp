package com.lucas.schiavini.client.utils

import com.lucas.schiavini.client.model.Movie
import com.lucas.schiavini.client.model.MovieAPI
import com.lucas.schiavini.client.model.MovieResult
import comlucasschiaviniclient.db.MoviesDB

fun MoviesDB.toMovie(): Movie {
    return Movie(
        id = id,
        originalTitle = originalTitle,
        director = director ?: "",
        title = title,
        overview = overview,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        voteAverage = voteAverage ?: "0.0",
        runtime = runtime ?: ""
    )
}

fun MovieAPI.toMovie(): Movie {
    return Movie(
        id = id.toLong(),
        originalTitle = originalTitle,
        director = director ?: "",
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage.toString(),
        runtime = runtime.toString()
    )
}

fun MovieResult.toMovie(): Movie {
    return Movie(
        id = id.toLong(),
        originalTitle = originalTitle,
        director = "",
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage.toString(),
        runtime = ""
    )
}
