package io.github.jitinsharma.reduxmovieexample.reducers

import io.github.jitinsharma.reduxmovieexample.actions.DisplayFavoriteMovies
import io.github.jitinsharma.reduxmovieexample.actions.DisplayNoFavoriteMessage
import io.github.jitinsharma.reduxmovieexample.states.FavoriteListState
import tw.geothings.rekotlin.Action

/**
 * Created by jsharma on 11/02/18.
 */

fun favoriteListReducer(action: Action, favoriteListState: FavoriteListState?): FavoriteListState {
    var state = favoriteListState ?: FavoriteListState()
    when (action) {
        is DisplayFavoriteMovies -> {
            state = state.copy(favorites = action.favorites, displayNoFavoriteMessage = false)
        }
        is DisplayNoFavoriteMessage -> {
            state = state.copy(favorites = null, displayNoFavoriteMessage = true)
        }
    }
    return state
}