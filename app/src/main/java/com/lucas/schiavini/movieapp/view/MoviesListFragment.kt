package com.lucas.schiavini.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucas.schiavini.movieapp.R
import com.lucas.schiavini.movieapp.model.Movie
import com.lucas.schiavini.movieapp.placeholder.PlaceholderContent
import com.lucas.schiavini.movieapp.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.movies_list_fragment.*

/**
 * A fragment representing a list of Items.
 */
class MoviesListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel

    private var columnCount = 1
//    private val movieListAdapter = MyMovieListAdapter(arrayListOf())
    private var movieListAdapter = MovieListAdapter(PlaceholderContent.ITEMS as ArrayList<Movie>)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        viewModel.refresh()

        moviesList.apply {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(context)
        }

        refreshLayout.setOnRefreshListener {
            moviesList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refresh()
//            viewModel.refreshBypassCache()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)
    }

    private fun observeViewModel() {
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies: List<Movie> ->
            movies.let {
                moviesList.visibility = View.VISIBLE
                movieListAdapter.updateMoviesList(movies)
            }
        })

        viewModel.moviesLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if(it) {
                    listError.visibility = View.GONE
                    moviesList.visibility = View.GONE
                }
            }
        })
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MoviesListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}