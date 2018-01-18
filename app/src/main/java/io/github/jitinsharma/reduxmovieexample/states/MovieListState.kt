package io.github.jitinsharma.reduxmovieexample.states

import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import tw.geothings.rekotlin.StateType

/**
 * Created by jsharma on 15/01/18.
 */

data class MovieListState(
        var movieObjects: List<MovieObject>? = null
) : StateType