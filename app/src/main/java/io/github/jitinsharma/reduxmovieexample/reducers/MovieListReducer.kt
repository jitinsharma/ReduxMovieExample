package io.github.jitinsharma.reduxmovieexample.reducers

import io.github.jitinsharma.reduxmovieexample.actions.displayMovies
import io.github.jitinsharma.reduxmovieexample.states.MovieListState
import tw.geothings.rekotlin.Action

/**
 * Created by jsharma on 15/01/18.
 */

fun movieListReducer(action: Action, movieListState: MovieListState?): MovieListState {
    return with(movieListState) {
        val state = this ?: MovieListState()
        when (action) {
            is displayMovies -> {
                state.copy(movieObjects = action.movieObjects)
            }
            else -> state
        }
    }
}