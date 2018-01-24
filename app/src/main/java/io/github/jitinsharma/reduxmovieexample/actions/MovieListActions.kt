package io.github.jitinsharma.reduxmovieexample.actions

import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import tw.geothings.rekotlin.Action

/**
 * Created by jsharma on 15/01/18.
 */

data class displayMovies(val movieObjects: List<MovieObject>) : Action

data class loadTopRatedMovies(val unit: Unit? = null) : Action