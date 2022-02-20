package com.lucas.schiavini.movieapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lucas.schiavini.movieapp.R
import com.lucas.schiavini.movieapp.databinding.MovieItemBinding
import com.lucas.schiavini.movieapp.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieListAdapter(
    private var moviesList: ArrayList<Movie>
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(), MovieClickListener {

    private lateinit var accessView : MovieItemBinding

    @SuppressLint("NotifyDataSetChanged")
    fun updateMoviesList(newMoviesList: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(newMoviesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MovieItemBinding>(inflater, R.layout.movie_item, parent, false)
        accessView = view
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = moviesList[position]
        accessView.movie = item
        accessView.listener = this
    }

    override fun onMovieClicked(v: View) {
        val id = v.movieId.text.toString()


//        Navigation.findNavController(view).navigate(action)
    }

    override fun getItemCount(): Int = moviesList.size

    inner class MovieViewHolder(binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var movie: Movie? = binding.movie
        var listener: MovieClickListener? = binding.listener
    }

}