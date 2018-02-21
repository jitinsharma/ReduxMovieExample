package io.github.jitinsharma.reduxmovieexample

import io.github.jitinsharma.reduxmovieexample.actions.Decrement
import io.github.jitinsharma.reduxmovieexample.actions.Increment
import io.github.jitinsharma.reduxmovieexample.actions.SetInitialCount
import io.github.jitinsharma.reduxmovieexample.reducers.favoriteCounterReducer
import io.github.jitinsharma.reduxmovieexample.states.FavoriteCounterState
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by jsharma on 21/02/18.
 */
@Suppress("FunctionName")
class FavoriteCounterReducerTest {

    @Test
    fun checkFavoriteCounterReducer_withInitialCount() {
        val action = SetInitialCount(5)
        val state = favoriteCounterReducer(action, null)
        assertEquals(state.favoriteCount, 5)
    }

    @Test
    fun checkFavoriteCounterReducer_withIncrement() {
        val action = Increment()
        val oldState = FavoriteCounterState(2)
        val newState = favoriteCounterReducer(action, oldState)
        assertEquals(oldState.favoriteCount, 2)
        assertEquals(newState.favoriteCount, 3)
    }

    @Test
    fun checkFavoriteCounterReducer_withDecrement() {
        val action = Decrement()
        val oldState = FavoriteCounterState(2)
        val newState = favoriteCounterReducer(action, oldState)
        assertEquals(oldState.favoriteCount, 2)
        assertEquals(newState.favoriteCount, 1)
    }
}