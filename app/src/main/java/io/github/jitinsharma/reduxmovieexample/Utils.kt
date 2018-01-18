package io.github.jitinsharma.reduxmovieexample

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by jsharma on 15/01/18.
 */

const val favoritesClicked = "favoritesClicked"
const val imageClicked = "imageClicked"
const val imageUrlPrefix = "http://image.tmdb.org/t/p/w185/"

fun ImageView.loadImage(url: String) {
    Picasso.with(context)
            .load(url)
            .into(this)
}