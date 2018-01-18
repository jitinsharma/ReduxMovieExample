package io.github.jitinsharma.reduxmovieexample.middlewares

import io.github.jitinsharma.reduxmovieexample.actions.displayMovies
import io.github.jitinsharma.reduxmovieexample.states.AppState
import tw.geothings.rekotlin.Middleware

/**
 * Created by jsharma on 15/01/18.
 */

internal val movieMiddleWare: Middleware<AppState> = { dispatch, getState ->
    { next ->
        { action ->
            when (action) {
                is displayMovies -> {

                }
            }
            next(action)
        }
    }
}