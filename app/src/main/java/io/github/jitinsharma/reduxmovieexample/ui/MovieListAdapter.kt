package io.github.jitinsharma.reduxmovieexample.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.jitinsharma.reduxmovieexample.R
import io.github.jitinsharma.reduxmovieexample.actions.addMovieToFavorites
import io.github.jitinsharma.reduxmovieexample.actions.removeMovieFromFavorites
import io.github.jitinsharma.reduxmovieexample.helpers.imageClicked
import io.github.jitinsharma.reduxmovieexample.helpers.imageUrlPrefix
import io.github.jitinsharma.reduxmovieexample.helpers.loadImage
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.store

/**
 * Created by jsharma on 15/01/18.
 */
class MovieListAdapter(private val movieObjects: List<MovieObject>,
                       private val listener: (clickType: String, movieObject: MovieObject) -> Unit) :
        RecyclerView.Adapter<MovieListAdapter.MovieItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieItemHolder =
            MovieItemHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_movie,
                    parent, false))

    override fun onBindViewHolder(holder: MovieItemHolder?, position: Int) {
        val current = movieObjects[position]
        holder?.apply {
            movieRating.text = current.voteAverage.toString()
            movieItemImage.loadImage(getImageUrl(current.posterPath.toString()))
            if (current.isFavorite) {
                addToFavorites.setImageDrawable(
                        ContextCompat.getDrawable(addToFavorites.context,
                                R.drawable.ic_favorite_red_24dp)
                )
            } else {
                addToFavorites.setImageDrawable(
                        ContextCompat.getDrawable(addToFavorites.context,
                                R.drawable.ic_favorite_border_black_24dp)
                )
            }
        }
    }

    private fun getImageUrl(posterPath: String): String = imageUrlPrefix + posterPath

    override fun getItemCount(): Int = movieObjects.size

    inner class MovieItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        lateinit var movieRating: TextView
        lateinit var addToFavorites: ImageView
        lateinit var movieItemImage: ImageView

        init {
            itemView?.apply {
                movieRating = findViewById(R.id.movieRating)
                addToFavorites = findViewById(R.id.addToFavorites)
                movieItemImage = findViewById(R.id.movieItemImage)
                addToFavorites.setOnClickListener {
                    movieObjects[adapterPosition].apply {
                        handleFavoriteClick()
                        onBindViewHolder(this@MovieItemHolder, adapterPosition)
                    }
                }
                movieItemImage.setOnClickListener {
                    listener.invoke(imageClicked, movieObjects[adapterPosition])
                }
            }
        }

        private fun MovieObject.handleFavoriteClick() {
            if (!isFavorite) {
                isFavorite = true
                store.dispatch(addMovieToFavorites(this))
            } else {
                isFavorite = false
                store.dispatch(removeMovieFromFavorites(this))
            }
        }
    }
}