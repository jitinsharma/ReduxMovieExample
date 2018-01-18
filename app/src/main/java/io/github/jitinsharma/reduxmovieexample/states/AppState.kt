package io.github.jitinsharma.reduxmovieexample.states

import tw.geothings.rekotlin.StateType

/**
 * Created by jsharma on 15/01/18.
 */

data class AppState(
        var movieListState: MovieListState? = null
) : StateType