package io.github.jitinsharma.reduxmovieexample.middlewares

import io.github.jitinsharma.reduxmovieexample.actions.displayMovies
import io.github.jitinsharma.reduxmovieexample.actions.initializeMovieList
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.states.AppState
import io.github.jitinsharma.reduxmovieexample.storage.MovieDBHelper
import tw.geothings.rekotlin.DispatchFunction
import tw.geothings.rekotlin.Middleware

/**
 * Created by jsharma on 30/01/18.
 */

internal val movieMiddleWare: Middleware<AppState> = { dispatch, getState ->
    { next ->
        { action ->
            when (action) {
                is initializeMovieList -> {
                    updateMoviesWithFavorites(action.movieObjects, dispatch)
                }
            }
            next(action)
        }
    }
}

private fun updateMoviesWithFavorites(movieObjects: List<MovieObject>, dispatch: DispatchFunction) {
    MovieDBHelper.getStoredMovies { list ->
        list?.let {
            movieObjects.forEach {
                if (list.contains(it)) {
                    it.isFavorite = true
                }
            }
            dispatch(displayMovies(movieObjects))
        }
    }
}