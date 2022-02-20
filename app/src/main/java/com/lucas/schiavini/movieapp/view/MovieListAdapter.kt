package com.lucas.schiavini.movieapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lucas.schiavini.movieapp.R
import com.lucas.schiavini.movieapp.databinding.MovieItemBinding
import com.lucas.schiavini.movieapp.model.Movie


class MovieListAdapter(
    private val moviesList: ArrayList<Movie>
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(), MovieClickListener {

    private lateinit var accessView : MovieItemBinding

    fun updateMoviesList(newMoviesList: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(newMoviesList)
        // TODO: notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<MovieItemBinding>(inflater, R.layout.movie_item, parent, false)
        accessView = view
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = moviesList[position]
        holder.movie = item
        holder.listener = this
    }

    override fun onMovieClicked(v: View) {

    }

    override fun getItemCount(): Int = moviesList.size

    inner class MovieViewHolder(binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var movie: Movie = binding.movie
        var listener: MovieClickListener = binding.listener
    }

}