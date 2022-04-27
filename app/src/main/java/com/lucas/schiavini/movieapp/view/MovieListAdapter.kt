package com.lucas.schiavini.movieapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lucas.schiavini.client.model.Movie
import com.lucas.schiavini.movieapp.databinding.MovieItemBinding
import com.lucas.schiavini.movieapp.util.loadImage
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieListDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

class MovieListAdapter(

) : ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(MovieListDiffCallback()) {

    lateinit var view : View

    private fun clickListener (v: View) {
        val id = v.movieId.text.toString()
        val action = MoviesListFragmentDirections.actionMovieFragmentToMovieDetail()
        action.movieId = id.toInt()
        findNavController(v).navigate(action)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        val view = binding.root
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position)) { newView -> clickListener(newView) }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(newMovie: Movie, clickListener: (View) -> Unit) {
            itemView.apply {
                loadImage(imageView, newMovie.posterPath)
                movieId.text = newMovie.id.toString()
                title.text = newMovie.title
                releaseDate.text = newMovie.releaseDate
                setOnClickListener(clickListener)
            }
        }

    }

}