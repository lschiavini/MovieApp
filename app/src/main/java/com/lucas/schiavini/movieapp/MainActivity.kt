package com.lucas.schiavini.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.lucas.schiavini.client.KTorSimpleClient
import com.lucas.schiavini.client.model.MovieListResult
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var listOfMovies: MovieListResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
        val movies = getAllMovies()
        Log.e("MOVIESSSSS", movies)
    }

    private fun getAllMovies(): String {
        var allMovies = ""
        runBlocking {
            launch {
                try{
//                    allMovies = KTorSimpleClient().getMovie("66")
                    listOfMovies = KTorSimpleClient().getMovies()
                    allMovies = listOfMovies.results[0].overview
                } catch (e:Exception){
                    Log.e("Error AAAAAA", e.toString())
                }

            }
        }
        return allMovies
    }


}