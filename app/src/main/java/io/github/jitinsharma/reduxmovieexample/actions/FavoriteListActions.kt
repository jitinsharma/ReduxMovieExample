package io.github.jitinsharma.reduxmovieexample.actions

import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import tw.geothings.rekotlin.Action

/**
 * Created by jsharma on 11/02/18.
 */

data class loadFavoriteMovies(val unit: Unit? = null): Action

data class displayFavoriteMovies(val favorites: List<MovieObject>): Action

data class displayNoFavoriteMessage(val unit: Unit? = null): Action