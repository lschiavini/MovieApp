package com.lucas.schiavini.client.model

data class Movie(
    val id: Long,
    val originalTitle: String,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: String?,
    val director: String,
    val runtime: String
)