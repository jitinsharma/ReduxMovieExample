package io.github.jitinsharma.reduxmovieexample.middlewares

import io.github.jitinsharma.reduxmovieexample.actions.DisplayMovies
import io.github.jitinsharma.reduxmovieexample.actions.InitializeMovieList
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.states.AppState
import io.github.jitinsharma.reduxmovieexample.storage.MovieDBHelper
import tw.geothings.rekotlin.DispatchFunction
import tw.geothings.rekotlin.Middleware

/**
 * Created by jsharma on 30/01/18.
 */

internal val movieMiddleWare: Middleware<AppState> = { dispatch, _ ->
    { next ->
        { action ->
            when (action) {
                is InitializeMovieList -> {
                    updateMoviesWithFavorites(action.movieObjects, dispatch)
                }
            }
            next(action)
        }
    }
}

private fun updateMoviesWithFavorites(movieObjects: List<MovieObject>, dispatch: DispatchFunction) {
    var favoriteMap: Map<Int, Long?> = mapOf()
    MovieDBHelper.getStoredMovies { list ->
        list?.let {
            favoriteMap = it.mapIndexed { index, movieObject -> index to movieObject.id }.toMap()
        }
        movieObjects.forEach {
            if (favoriteMap.containsValue(it.id)) {
                it.isFavorite = true
            }
        }
        dispatch(DisplayMovies(movieObjects))
    }
}