package io.github.jitinsharma.reduxmovieexample.middlewares

import io.github.jitinsharma.reduxmovieexample.actions.*
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.states.AppState
import io.github.jitinsharma.reduxmovieexample.storage.MovieDBHelper
import tw.geothings.rekotlin.DispatchFunction
import tw.geothings.rekotlin.Middleware

/**
 * Created by jsharma on 15/01/18.
 */

internal val databaseMiddleWare: Middleware<AppState> = { dispatch, getState ->
    { next ->
        { action ->
            when (action) {
                is addMovieToFavorites -> {
                    insertMovieAsync(action.movieObject, dispatch)
                }
                is removeMovieFromFavorites -> {
                    deleteMovieAsync(action.movieObject, dispatch)
                }
                is checkForFavorites -> {
                    getFavoriteCount(dispatch)
                }
            }
            next(action)
        }
    }
}

fun insertMovieAsync(movieObject: MovieObject, dispatch: DispatchFunction) {
    MovieDBHelper.insertMovieAsync(movieObject) {
        dispatch.invoke(increment())
    }
}

fun deleteMovieAsync(movieObject: MovieObject, dispatch: DispatchFunction) {
    MovieDBHelper.deleteMovieAsync(movieObject) {
        dispatch.invoke(decrement())
    }
}

fun getFavoriteCount(dispatch: DispatchFunction) {
    MovieDBHelper.getStoredMovies { list ->
        list?.apply {
            dispatch.invoke(setInitialCount(size))
        }
    }
}