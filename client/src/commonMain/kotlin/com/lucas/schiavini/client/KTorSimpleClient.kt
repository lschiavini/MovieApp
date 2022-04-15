package com.lucas.schiavini.client

import com.lucas.schiavini.client.model.Movie
import com.lucas.schiavini.client.model.MovieListResult
import com.lucas.schiavini.client.model.MovieResult
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


    suspend fun mockGetMovies(): MovieListResult {
        delay(1000L)
        val listOfMovies = listOf(
            MovieResult(
                adult=false,
                backdropPath="/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg",
                genreIds= listOf(28, 12, 878), id=634649,
                originalLanguage="en",
                originalTitle="Spider-Man: No Way Home",
                overview="Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                popularity=7517.432,
                posterPath="/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                releaseDate="2021-12-15",
                title="Spider-Man: No Way Home", video=false, voteAverage=8.3, voteCount=8460),
            MovieResult(
                adult=false,
                backdropPath="/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg",
                genreIds=listOf(16, 35, 10751, 14), id=568124,
                originalLanguage="en",
                originalTitle="Encanto",
                overview="The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope",
                popularity=3850.439,
                posterPath="/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                releaseDate="2021-11-24",
                title="Encanto", video=false, voteAverage=7.8, voteCount=4767),
            MovieResult(
                adult=false,
                backdropPath="/6qkeXdIEwqOuOWuxsomwnin2RdD.jpg",
                genreIds=listOf(28, 12, 53, 10752), id=476669,
                originalLanguage="en",
                originalTitle="The King's Man",
                overview="As a collection of history's worst tyrants and criminal masterminds gather to plot a war to wipe out millions, one man must race against time to stop them.",
                popularity=3507.4,
                posterPath="/aq4Pwv5Xeuvj6HZKtxyd23e6bE9.jpg",
                releaseDate="2021-12-22",
                title="The King's Man", video=false, voteAverage=7.1, voteCount=1503),
            MovieResult(
                adult=false,
                backdropPath="/usaZV7KB6Man9Rm9TyDAeQf7uVD.jpg",
                genreIds=listOf(27, 9648, 53), id=646385,
                originalLanguage="en",
                originalTitle="Scream",
                overview="Twenty-five years after a streak of brutal murders shocked the quiet town of Woodsboro, a new killer has donned the Ghostface mask and begins targeting a group of teenagers to resurrect secrets from the town’s deadly past.",
                popularity=3560.934,
                posterPath="/kZNHR1upJKF3eTzdgl5V8s8a4C3.jpg",
                releaseDate="2022-01-12",
                title="Scream", video=false, voteAverage=7.0, voteCount=698),
            MovieResult(
                adult=false,
                backdropPath="/eG0oOQVsniPAuecPzDD1B1gnYWy.jpg",
                genreIds=listOf(16, 35, 12, 10751), id=774825,
                originalLanguage="en",
                originalTitle="The Ice Age Adventures of Buck Wild",
                overview="The fearless one-eyed weasel Buck teams up with mischievous possum brothers Crash & Eddie as they head off on a new adventure into Buck's home: The Dinosaur World",
                popularity=2960.414,
                posterPath="/zzXFM4FKDG7l1ufrAkwQYv2xvnh.jpg",
                releaseDate="2022-01-28",
                title="The Ice Age Adventures of Buck Wild", video=false, voteAverage=7.3, voteCount=647),
            MovieResult(
                adult=false,
                backdropPath="/mqDnDhG5N6fn1H4MKQqr8E5wfeK.jpg",
                genreIds=listOf(80, 18, 53), id=597208,
                originalLanguage="en",
                originalTitle="Nightmare Alley",
                overview="An ambitious carnival man with a talent for manipulating people with a few well-chosen words hooks up with a female psychiatrist who is even more dangerous than he is.",
                popularity=2728.228,
                posterPath="/680klE0dIreQQOyWKFgNnCAJtws.jpg",
                releaseDate="2021-12-02",
                title="Nightmare Alley", video=false, voteAverage=7.2, voteCount=867),
            MovieResult(
                adult=false,
                backdropPath="/mruT954ve6P1zquaRs6XG0hA5k9.jpg",
                genreIds=listOf(53), id=80051,
                originalLanguage="en",
                originalTitle="Kimi",
                overview="A tech worker with agoraphobia discovers recorded evidence of a violent crime but is met with resistance when she tries to report it. Seeking justice, she must do the thing she fears the most: she must leave her apartment.",
                popularity=2785.625,
                posterPath="/okNgwtxIWzGsNlR3GsOS0i0Qgbn.jpg",
                releaseDate="2022-02-10",
                title="Kimi", video=false, voteAverage=6.3, voteCount=153),
            MovieResult(
                adult=false,
                backdropPath="/qBLEWvJNVsehJkEJqIigPsWyBse.jpg",
                genreIds=listOf(16, 10751, 14, 35, 12), id=585083,
                originalLanguage="en",
                originalTitle="Hotel Transylvania: Transformania",
                overview="When Van Helsing's mysterious invention, the Monsterfication Ray, goes haywire, Drac and his monster pals are all transformed into humans, and Johnny becomes a monster. In their new mismatched bodies, Drac and Johnny must team up and race across the globe to find a cure before it's too late, and before they drive each other crazy.",
                popularity=2325.902,
                posterPath="/teCy1egGQa0y8ULJvlrDHQKnxBL.jpg",
                releaseDate="2022-02-25",
                title="Hotel Transylvania: Transformania", video=false, voteAverage=6.9, voteCount=156),
            MovieResult(
                adult=false,
                backdropPath="/cugmVwK0N4aAcLibelKN5jWDXSx.jpg",
                genreIds=listOf(16, 28, 14, 12), id=768744,
                originalLanguage="ja",
                originalTitle="僕のヒーローアカデミア THE MOVIE ワールド ヒーローズ ミッション",
                overview="A mysterious group called Humarize strongly believes in the Quirk Singularity Doomsday theory which states that when quirks get mixed further in with future generations, that power will bring forth the end of humanity. In order to save everyone, the Pro-Heroes around the world ask UA Academy heroes-in-training to assist them and form a world-classic selected hero team. It is up to the heroes to save the world and the future of heroes in what is the most dangerous crisis to take place yet in My Hero Academia.",
                popularity=2017.573,
                posterPath="/4NUzcKtYPKkfTwKsLjwNt8nRIXV.jpg",
                releaseDate="2021-08-06",
                title="My Hero Academia: World Heroes' Mission", video=false, voteAverage=7.4, voteCount=79),
            MovieResult(
                adult=false,
                backdropPath="/kQM7o3NIkruIZLoQ9E2XzZQ8Ujl.jpg",
                genreIds=listOf(28, 35, 80, 10749, 53), id=783461,
                originalLanguage="hi",
                originalTitle="लूप लपेटा",
                overview="When her boyfriend loses a mobster's cash, Savi races against the clock to save the day — if only she can break out of a curious cycle of dead ends",
                popularity=2076.654,
                posterPath="/mtLk7vugGWtMkO913Fg8zfM1YET.jpg"
                ,        releaseDate="2022-02-04",
                title="Looop Lapeta", video=false, voteAverage=5.7, voteCount=17),
            MovieResult(
                adult=false,
                backdropPath="/tutaKitJJIaqZPyMz7rxrhb4Yxm.jpg",
                genreIds=listOf(35, 16, 10751, 10402), id=438695,
                originalLanguage="en",
                originalTitle="Sing 2",
                overview="Buster and his new cast now have their sights set on debuting a new show at the Crystal Tower Theater in glamorous Redshore City. But with no connections, he and his singers must sneak into the Crystal Entertainment offices, run by the ruthless wolf mogul Jimmy Crystal, where the gang pitches the ridiculous idea of casting the lion rock legend Clay Calloway in their show. Buster must embark on a quest to find the now-isolated Clay and persuade him to return to the stage.",
                popularity=1956.577,
                posterPath="/aWeKITRFbbwY8txG5uCj4rMCfSP.jpg",
                releaseDate="2021-12-01",
                title="Sing 2", video=false, voteAverage=8.2, voteCount=2040),
            MovieResult(
                adult=false,
                backdropPath="/c6H7Z4u73ir3cIoCteuhJh7UCAR.jpg",
                genreIds=listOf(28, 12, 14, 878), id=524434,
                originalLanguage="en",
                originalTitle="Eternals",
                overview="The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                popularity=2099.68,
                posterPath="/bcCBq9N1EMo3daNIjWJ8kYvrQm6.jpg",
                releaseDate="2021-11-03",
                title="Eternals", video=false, voteAverage=7.2, voteCount=4561),
            MovieResult(
                adult=false,
                backdropPath="/aTSA5zMWlVFTYBIZxTCMbLkfOtb.jpg",
                genreIds=listOf(27), id=63272,
                originalLanguage="en",
                originalTitle="Texas Chainsaw Massacre",
                overview="In this sequel, influencers looking to breathe new life into a Texas ghost town encounter Leatherface, an infamous killer who wears a mask of human skin.",
                popularity=2380.14,
                posterPath="/meRIRfADEGVo65xgPO6eZvJ0CRG.jpg",
                releaseDate="2022-02-18",
                title="Texas Chainsaw Massacre", video=false, voteAverage=5.1, voteCount=419),
            MovieResult(
                adult=false,
                backdropPath="/eVSa4TpyXbOdk9fXSD6OcORJGtv.jpg",
                genreIds=listOf(53), id=80311,
                originalLanguage="en",
                originalTitle="The Requin",
                overview="A couple on a romantic getaway find themselves stranded at sea when a tropical storm sweeps away their villa. In order to survive, they are forced to fight the elements, while sharks circle below.",
                popularity=2160.331,
                posterPath="/i0z8g2VRZP3dhVvvSMilbOZMKqR.jpg",
                releaseDate="2022-01-28",
                title="The Requin", video=false, voteAverage=4.5, voteCount=55),
            MovieResult(
                adult=false,
                backdropPath="/wMUPT99gw6IB9OVvC46rF8wHIRt.jpg",
                genreIds=listOf(28, 80, 14), id=890656,
                originalLanguage="en",
                originalTitle="Fistful of Vengeance",
                overview="A revenge mission becomes a fight to save the world from an ancient threat when superpowered assassin Kai tracks a killer to Bangkok.",
                popularity=1623.29,
                posterPath="/3cccEF9QZgV9bLWyupJO41HSrOV.jpg",
                releaseDate="2022-02-17",
                title="Fistful of Vengeance", video=false, voteAverage=5.4, voteCount=69),
            MovieResult(
                adult=false,
                backdropPath="/dK12GIdhGP6NPGFssK2Fh265jyr.jpg",
                genreIds=listOf(28, 35, 80, 53), id=512195,
                originalLanguage="en",
                originalTitle="Red Notice",
                overview="An Interpol-issued Red Notice is a global alert to hunt and capture the world's most wanted. But when a daring heist brings together the FBI's top profiler and two rival criminals, there's no telling what will happen",
                popularity=1764.309,
                posterPath="/wdE6ewaKZHr62bLqCn7A2DiGShm.jpg"
                ,        releaseDate="2021-11-04",
                title="Red Notice", video=false, voteAverage=6.8, voteCount=3120),
            MovieResult(
                adult=false,
                backdropPath="/ilty8eu65u6vCJpyMva9ele8Qtm.jpg",
                genreIds=listOf(35, 10749, 10402), id=615904,
                originalLanguage="en",
                originalTitle="Marry Me",
                overview="Music superstars Kat Valdez and Bastian are getting married before a global audience of fans. But when Kat learns, seconds before her vows, that Bastian has been unfaithful, she decides to marry Charlie, a stranger in the crowd, instead.",
                popularity=1741.374,
                posterPath="/ko1JVbGj4bT8IhCWqjBQ6ZtF2t.jpg",
                releaseDate="2022-02-09",
                title="Marry Me", video=false, voteAverage=6.9, voteCount=195),
            MovieResult(
                adult=false,
                backdropPath="/yuvFyfOAO2UB5YP0HKgu8imtJul.jpg",
                genreIds=listOf(28, 12), id=335787,
                originalLanguage="en",
                originalTitle="Uncharted",
                overview="A young street-smart, Nathan Drake and his wisecracking partner Victor “Sully” Sullivan embark on a dangerous pursuit of “the greatest treasure never found” while also tracking clues that may lead to Nathan’s long-lost brother.",
                popularity=1338.427,
                posterPath="/tlZpSxYuBRoVJBOpUrPdQe9FmFq.jpg",
                releaseDate="2022-02-10",
                title="Uncharted", video=false, voteAverage=7.2, voteCount=511),
            MovieResult(
                adult=false,
                backdropPath="/EnDlndEvw6Ptpp8HIwmRcSSNKQ.jpg",
                genreIds=listOf(14, 35, 12), id=425909,
                originalLanguage="en",
                originalTitle="Ghostbusters: Afterlife",
                overview="When a single mom and her two kids arrive in a small town, they begin to discover their connection to the original Ghostbusters and the secret legacy their grandfather left behind.",
                popularity=1556.443,
                posterPath="/sg4xJaufDiQl7caFEskBtQXfD4x.jpg",
                releaseDate="2021-11-11",
                title="Ghostbusters: Afterlife", video=false, voteAverage=7.7, voteCount=2137),
            MovieResult(
                adult=false,
                backdropPath="/vIgyYkXkg6NC2whRbYjBD7eb3Er.jpg",
                genreIds=listOf(878, 28, 12), id=580489,
                originalLanguage="en",
                originalTitle="Venom: Let There Be Carnage",
                overview="After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                popularity=1367.529,
                posterPath="/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                releaseDate="2021-09-30",
                title="Venom: Let There Be Carnage", video=false, voteAverage=7.1, voteCount=6440)
        )
        return MovieListResult(1, listOfMovies, 1, 10)
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




