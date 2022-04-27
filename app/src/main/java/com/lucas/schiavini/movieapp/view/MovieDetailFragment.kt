package com.lucas.schiavini.movieapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.lucas.schiavini.client.model.Movie
import com.lucas.schiavini.client.model.MovieAPI
import com.lucas.schiavini.movieapp.AndroidRepository
import com.lucas.schiavini.movieapp.R
import com.lucas.schiavini.movieapp.databinding.MovieDetailFragmentBinding
import com.lucas.schiavini.movieapp.viewmodel.MovieDetailViewModel

class MovieDetailFragment : Fragment() {


    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var dataBinding: MovieDetailFragmentBinding
    private var movieId = 0
    private var currentMovieAPI: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            movieId = MovieDetailFragmentArgs.fromBundle(it).movieId
        }
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        val androidRepo = AndroidRepository(requireContext())
        viewModel.repository = androidRepo.repository
        viewModel.fetchMovie(movieId)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { movieSelected ->
            currentMovieAPI = movieSelected
            movieSelected?.let {
                dataBinding.movie = it
            }
        })

    }

}