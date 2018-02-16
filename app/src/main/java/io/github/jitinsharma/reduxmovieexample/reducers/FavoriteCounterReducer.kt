package io.github.jitinsharma.reduxmovieexample.reducers

import io.github.jitinsharma.reduxmovieexample.actions.Decrement
import io.github.jitinsharma.reduxmovieexample.actions.Increment
import io.github.jitinsharma.reduxmovieexample.actions.SetInitialCount
import io.github.jitinsharma.reduxmovieexample.states.FavoriteCounterState
import tw.geothings.rekotlin.Action

/**
 * Created by jsharma on 30/01/18.
 */

fun favoriteCounterReducer(action: Action, favoriteCounterState: FavoriteCounterState?)
        : FavoriteCounterState {
    var state = favoriteCounterState ?: FavoriteCounterState()
    when (action) {
        is SetInitialCount -> {
            state = state.copy(favoriteCount = action.count)
        }
        is Increment -> {
            state = state.copy(favoriteCount = state.favoriteCount + 1)
        }
        is Decrement -> {
            state = state.copy(favoriteCount = state.favoriteCount - 1)
        }
    }
    return state
}