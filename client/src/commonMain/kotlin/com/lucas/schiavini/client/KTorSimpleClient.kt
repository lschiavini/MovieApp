package com.lucas.schiavini.client

import com.lucas.schiavini.client.model.Movie
import com.lucas.schiavini.client.model.MovieListResult
import io.ktor.client.HttpClient
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.get
import io.ktor.client.statement.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json

import kotlin.native.concurrent.SharedImmutable
import kotlinx.serialization.decodeFromString


@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher
class KTorSimpleClient {
    private val movieApiAddress = "https://api.themoviedb.org/3"
    private val apiKey = "4473ae667f6dd6ef7e5ba9ac5204a9c8"
    val client = HttpClient() {
        install(ContentNegotiation) {
            Json {
                prettyPrint = true
                isLenient = true
            }
        }
    }

    suspend fun getMovies(): MovieListResult {
        val httpResponse = client.get {
            url("$movieApiAddress/discover/movie")
            parameter("sort_by", "popularity.desc")
            parameter("api_key", apiKey)
        }
        return Json.decodeFromString(httpResponse.bodyAsText())
    }

    suspend fun getMovie(id: String): Movie {
        val httpResponse = client.get {
            url("$movieApiAddress/movie/$id")
            parameter("append_to_response", "credits")
            parameter("api_key", apiKey)
        }
        val movie = Json.decodeFromString<Movie>(httpResponse.bodyAsText())
        val director = getDirectorFromCredits(movie)
        movie.director = director
        return movie
    }

    private fun getDirectorFromCredits(movie: Movie) : String {
        return try{
            val credits = movie.credits
            val crew = credits.crew
            var director = ""
            for(element in crew) {
                val currJob = element.job
                if (currJob == "Director") {
                    director = element.name
                    break
                }
            }
            director
        } catch(e: Exception) {
            ""
        }
    }

}




