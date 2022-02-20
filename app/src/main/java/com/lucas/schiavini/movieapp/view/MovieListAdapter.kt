package com.lucas.schiavini.movieapp.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.lucas.schiavini.movieapp.databinding.FragmentItemBinding
import com.lucas.schiavini.movieapp.model.Movie

class MovieListAdapter(
    private val moviesList: ArrayList<Movie>
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {


    fun updateMoviesList(newMoviesList: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(newMoviesList)
        // TODO: notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = moviesList[position]
        holder.idView.text = item.title
        holder.contentView.text = item.title
    }

    override fun getItemCount(): Int = moviesList.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}