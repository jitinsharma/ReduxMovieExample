package io.github.jitinsharma.reduxmovieexample.middlewares

import io.github.jitinsharma.reduxmovieexample.actions.DisplayMovies
import io.github.jitinsharma.reduxmovieexample.actions.InitializeMovieList
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.states.AppState
import io.github.jitinsharma.reduxmovieexample.storage.MovieDBHelper
import io.github.jitinsharma.reduxmovieexample.storage.MovieDao
import tw.geothings.rekotlin.DispatchFunction
import tw.geothings.rekotlin.Middleware
import javax.inject.Inject

/**
 * Created by jsharma on 30/01/18.
 */

class MovieMiddleWare @Inject constructor(val movieDao: MovieDao) : Middleware<AppState> {
    override fun invoke(dispatch: DispatchFunction, state: () -> AppState?): (DispatchFunction) -> DispatchFunction {
        return { next ->
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
        MovieDBHelper.getStoredMovies(movieDao) { list ->
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
}
