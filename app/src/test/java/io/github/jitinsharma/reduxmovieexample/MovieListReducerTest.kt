package io.github.jitinsharma.reduxmovieexample

import io.github.jitinsharma.reduxmovieexample.actions.DisplayMovies
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.reducers.movieListReducer
import org.junit.Assert
import org.junit.Test

/**
 * Created by jsharma on 21/02/18.
 */
@Suppress("FunctionName")
class MovieListReducerTest {

    @Test
    fun checkMovieListReducer_withDisplayingMovies() {
        val action = DisplayMovies(listOf(
                MovieObject()
        ))
        val state = movieListReducer(action, null)
        Assert.assertEquals(state.movieObjects?.size, 1)
    }
}