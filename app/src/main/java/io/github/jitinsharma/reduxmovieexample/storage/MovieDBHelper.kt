package io.github.jitinsharma.reduxmovieexample.storage

import io.github.jitinsharma.reduxmovieexample.MovieApplication
import io.github.jitinsharma.reduxmovieexample.helpers.asyncCoroutinesExecutor
import io.github.jitinsharma.reduxmovieexample.models.MovieObject

/**
 * Created by jsharma on 20/01/18.
 */

class MovieDBHelper {

    companion object {

        fun insertMovieAsync(movieObject: MovieObject, listener: () -> Unit) {
            asyncCoroutinesExecutor(
                    heavyFunction = { MovieApplication.movieDataBase?.movieDao()?.insert(movieObject) },
                    response = { listener.invoke() }
            )
        }

        fun getStoredMovies(listener: (List<MovieObject>?) -> Unit) {
            asyncCoroutinesExecutor(
                    heavyFunction = { MovieApplication.movieDataBase?.movieDao()?.getAll() },
                    response = { listener.invoke(it) }
            )
        }

        fun deleteMovieAsync(movieObject: MovieObject, listener: () -> Unit) {
            asyncCoroutinesExecutor(
                    heavyFunction = { MovieApplication.movieDataBase?.movieDao()?.delete(movieObject) },
                    response = { listener.invoke() }
            )
        }
    }
}