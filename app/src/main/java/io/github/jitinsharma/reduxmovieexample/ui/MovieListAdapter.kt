package io.github.jitinsharma.reduxmovieexample.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.github.jitinsharma.reduxmovieexample.*
import io.github.jitinsharma.reduxmovieexample.models.MovieObject

/**
 * Created by jsharma on 15/01/18.
 */
class MovieListAdapter(private val movieObjects: List<MovieObject>,
                       private val listener: (clickType: String) -> Unit):
        RecyclerView.Adapter<MovieListAdapter.MovieItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieItemHolder =
            MovieItemHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_movie,
                    parent, false))

    override fun onBindViewHolder(holder: MovieItemHolder?, position: Int) {
        val current = movieObjects[position]
        holder?.apply {
            movieRating.text = current.voteAverage.toString()
            movieItemImage.loadImage(getImageUrl(current.posterPath.toString()))
            movieItemImage.setOnClickListener {
                listener.invoke(imageClicked)
            }
            addToFavorites.setOnClickListener {
                listener.invoke(favoritesClicked)
            }
        }
    }

    private fun getImageUrl(posterPath: String) : String = imageUrlPrefix + posterPath

    override fun getItemCount(): Int  = movieObjects.size

    inner class MovieItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        lateinit var movieRating : TextView
        lateinit var addToFavorites : ImageView
        lateinit var movieItemImage : ImageView

        init {
            itemView?.apply {
                movieRating = findViewById(R.id.movieRating)
                addToFavorites = findViewById(R.id.addToFavorites)
                movieItemImage = findViewById(R.id.movieItemImage)
            }
        }
    }
}