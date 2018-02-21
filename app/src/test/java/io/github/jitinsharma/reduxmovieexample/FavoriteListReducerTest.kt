package io.github.jitinsharma.reduxmovieexample

import io.github.jitinsharma.reduxmovieexample.actions.DisplayFavoriteMovies
import io.github.jitinsharma.reduxmovieexample.actions.DisplayNoFavoriteMessage
import io.github.jitinsharma.reduxmovieexample.models.MovieObject
import io.github.jitinsharma.reduxmovieexample.reducers.favoriteListReducer
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by jsharma on 21/02/18.
 */
@Suppress("FunctionName")
class FavoriteListReducerTest {

    @Test
    fun checkFavoriteListReducer_withFavoritesAvailable() {
        val action = DisplayFavoriteMovies(listOf(
                MovieObject()
        ))
        val state = favoriteListReducer(action, null)
        assertEquals(state.favorites?.size, 1)
        assertFalse(state.displayNoFavoriteMessage)
    }

    @Test
    fun checkFavoriteListReducer_withNoFavorites() {
        val action = DisplayNoFavoriteMessage()
        val state = favoriteListReducer(action, null)
        assertNull(state.favorites)
        assertTrue(state.displayNoFavoriteMessage)
    }
}