package io.github.jitinsharma.reduxmovieexample.actions

import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import tw.geothings.rekotlin.Action

/**
 * Created by jsharma on 11/02/18.
 */

class LoadFavoriteMovies : Action

class DisplayFavoriteMovies(val favorites: List<MovieObject>) : Action

class DisplayNoFavoriteMessage : Action