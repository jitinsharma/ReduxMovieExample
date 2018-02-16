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

internal val databaseMiddleWare: Middleware<AppState> = { dispatch, _ ->
    { next ->
        { action ->
            when (action) {
                is AddMovieToFavorites -> {
                    insertMovieAsync(action.movieObject, dispatch)
                }
                is RemoveMovieFromFavorites -> {
                    deleteMovieAsync(action.movieObject, dispatch)
                }
                is CheckForFavorites -> {
                    getFavoriteCount(dispatch)
                }
                is LoadFavoriteMovies -> {
                    getFavoriteMovies(dispatch)
                }
            }
            next(action)
        }
    }
}

private fun insertMovieAsync(movieObject: MovieObject, dispatch: DispatchFunction) {
    MovieDBHelper.insertMovieAsync(movieObject) {
        dispatch.invoke(Increment())
    }
}

private fun deleteMovieAsync(movieObject: MovieObject, dispatch: DispatchFunction) {
    MovieDBHelper.deleteMovieAsync(movieObject) {
        dispatch.invoke(Decrement())
    }
}

private fun getFavoriteCount(dispatch: DispatchFunction) {
    MovieDBHelper.getStoredMovies { list ->
        list?.apply {
            dispatch.invoke(SetInitialCount(size))
        }
    }
}

private fun getFavoriteMovies(dispatch: DispatchFunction) {
    MovieDBHelper.getStoredMovies { list ->
        list?.apply {
            if (isEmpty()) {
                dispatch(DisplayNoFavoriteMessage())
            } else {
                dispatch(DisplayFavoriteMovies(this))
            }
        }
    }
}
