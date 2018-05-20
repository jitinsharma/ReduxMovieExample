package io.github.jitinsharma.reduxmovieexample.storage

import io.github.jitinsharma.reduxmovieexample.helpers.asyncCoroutinesExecutor
import io.github.jitinsharma.reduxmovieexample.models.MovieObject

/**
 * Created by jsharma on 20/01/18.
 */

object MovieDBHelper {

    fun insertMovieAsync(movieDao: MovieDao, movieObject: MovieObject, listener: () -> Unit) {
        asyncCoroutinesExecutor(
                heavyFunction = { movieDao.insert(movieObject) },
                response = { listener.invoke() }
        )
    }

    fun getStoredMovies(movieDao: MovieDao, listener: (List<MovieObject>?) -> Unit) {
        asyncCoroutinesExecutor(
                heavyFunction = { movieDao.getAll() },
                response = { listener.invoke(it) }
        )
    }

    fun deleteMovieAsync(movieDao: MovieDao, movieObject: MovieObject, listener: () -> Unit) {
        asyncCoroutinesExecutor(
                heavyFunction = { movieDao.delete(movieObject) },
                response = { listener.invoke() }
        )
    }

}
