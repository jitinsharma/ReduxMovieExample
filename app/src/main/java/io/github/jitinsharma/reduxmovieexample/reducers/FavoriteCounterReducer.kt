package io.github.jitinsharma.reduxmovieexample.reducers

import io.github.jitinsharma.reduxmovieexample.actions.decrement
import io.github.jitinsharma.reduxmovieexample.actions.increment
import io.github.jitinsharma.reduxmovieexample.actions.setInitialCount
import io.github.jitinsharma.reduxmovieexample.states.FavoriteCounterState
import tw.geothings.rekotlin.Action

/**
 * Created by jsharma on 30/01/18.
 */

fun favoriteCounterReducer(action: Action, favoriteCounterState: FavoriteCounterState?)
        : FavoriteCounterState {
    var state = favoriteCounterState ?: FavoriteCounterState()
    when (action) {
        is setInitialCount -> {
            state = state.copy(favoriteCount = action.count)
        }
        is increment -> {
            state = state.copy(favoriteCount = state.favoriteCount + 1)
        }
        is decrement -> {
            state = state.copy(favoriteCount = state.favoriteCount - 1)
        }
    }
    return state
}